package com.android.testapp.data

import com.android.testapp.http.BookService
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.util.*

object BookUtils {
    /**
     * POK(j)O implements simple tool to books json data casting
     */
    @Throws(JSONException::class)
    private fun fromJSONObject(jsonObject: JSONObject): Book {
        try {
            val author = jsonObject.getString(BookService.AUTHOR_KEY)
            val title = jsonObject.getString(BookService.TITLE_KEY)
            val imageLink = jsonObject.getString(BookService.IMAGE_KEY)
            val isbn = jsonObject.getString(BookService.ISBN_KEY)
            val description = jsonObject.getString(BookService.DESCRIPTION_KEY)
            val year = jsonObject.getString(BookService.YEAR_KEY)
            return BookImpl(title, author, year, isbn, description, imageLink)
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        throw JSONException("There are no such fields in object $jsonObject")
    }

    @Throws(JSONException::class)
    fun fromJSONArray(array: JSONArray): List<Book> {
        val books =
            ArrayList<Book>()
        for (i in 0 until array.length()) {
            books.add(fromJSONObject(array.getJSONObject(i)))
        }
        return books
    }
}