package com.android.testapp.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.android.testapp.data.Book
import com.android.testapp.data.BookUtils
import com.android.testapp.http.BookService
import com.android.testapp.http.RetrofitBookRequest
import okhttp3.ResponseBody
import org.json.JSONException
import retrofit2.Callback
import org.json.JSONObject
import retrofit2.Call

import retrofit2.Response

class PageViewModel : ViewModel() {

    /**
     * Class represents a part of MVVM structure components - ViewModel
     * Main goal of this class is access and storage of books list
     */

    var booklist: MutableLiveData<List<Book>> = MutableLiveData()

    fun getBookList() = booklist

    /**
     * Initialization of modelView
     */
    init {
        RetrofitBookRequest(object : Callback<ResponseBody> {
            override fun onResponse(
                call: Call<ResponseBody?>,
                response: Response<ResponseBody?>
            ) {
                val jsonObject = JSONObject(response.body()?.string())
                val array = jsonObject.getJSONArray(BookService.BOOKS_ARRAY_TITLE)
                booklist.value = BookUtils.fromJSONArray(array)
            }

            /**
             * Failure request case will throw json exception
             */
            override fun onFailure(
                call: Call<ResponseBody?>,
                t: Throwable
            ) {
                throw JSONException("Check request parameters")
            }
        }
        ).sendRequest()
    }

    private val _index = MutableLiveData<Int>()
    val text: LiveData<String> = Transformations.map(_index) {
        "Favorite section isn't implemented"
    }

    fun setIndex(index: Int) {
        _index.value = index
    }
}