package com.android.testapp.ui.main.fragments

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.android.testapp.R
import com.android.testapp.data.Book
import com.android.testapp.data.BookImpl
import com.android.testapp.databinding.BooksInfoPageBinding
import com.android.testapp.http.BookService
import com.android.testapp.http.PicassoHelper

class BookInfoPageActivity : AppCompatActivity() {

    private lateinit var binding: BooksInfoPageBinding

    /**
     * Single page activity with constant Book object review
     */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.books_info_page)

        //Extras extraction
        val extras = intent.extras!!
        val author = extras.getString(BookService.AUTHOR_KEY)
        val description = extras.getString(BookService.DESCRIPTION_KEY)
        val isbn = extras.getString(BookService.ISBN_KEY)
        val title = extras.getString(BookService.TITLE_KEY)
        val year = extras.getString(BookService.YEAR_KEY)
        val imageLink = extras.getString(BookService.IMAGE_KEY)

        book = BookImpl(title!!, author!!, year!!, isbn!!, description!!, imageLink!!)
        binding.setBook(book)
        val image =
            binding.getRoot().findViewById<ImageView>(R.id.book_main_image)
        PicassoHelper.draw(this, image, (book as BookImpl).getImageLink())
    }

    companion object {
        private var book: Book? = null
    }
}