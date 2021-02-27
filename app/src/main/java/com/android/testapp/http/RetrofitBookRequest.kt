package com.android.testapp.http

import android.os.StrictMode
import okhttp3.ResponseBody
import retrofit2.Callback
import retrofit2.Retrofit

class RetrofitBookRequest(callback: Callback<ResponseBody>) :
    Runnable {

    /**
     * Implements single Http request using retrofit and Additional thread with extended strict policy
     *
     * @author me
     * @since 27.03.2020
     */

    private val host = "https://gist.githubusercontent.com/eugeneitrex/"
    private val books =
        "53359306d4c8b54323f8a67727e5a7ee/raw/cff09d023e82a408c574cc0c136268ea7e66e784/"

    /**
     * Book Service
     */
    private val service: BookService

    /**
     * Response callback
     */
    private val callback: Callback<ResponseBody>

    /**
     * Runs separated thread for single request.
     */
    fun sendRequest() {
        Thread(this).start()
    }

    /**
     * Response on new separated thread
     */
    override fun run() {
        StrictMode.setThreadPolicy(
            StrictMode.ThreadPolicy.Builder().permitAll().build()
        ) //Allows thread do networking stuff and so on
        val call = service.getBook(books)
        call.enqueue(callback)
    }

    /**
     * Constructor for Books request
     */
    init {
        service =
            Retrofit.Builder().baseUrl(host).build().create(BookService::class.java)
        this.callback = callback
    }
}