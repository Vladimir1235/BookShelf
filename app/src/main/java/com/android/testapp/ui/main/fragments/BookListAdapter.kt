package com.android.testapp.ui.main.fragments

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import com.android.testapp.R
import com.android.testapp.data.Book
import com.android.testapp.databinding.BooksListRowBinding
import com.android.testapp.http.PicassoHelper

class BookListAdapter(
    context: Context,
    resource: Int,
    books: List<Book>
) : ArrayAdapter<Book?>(context, resource) {

    /**
     * Simple list view adapteer extends ArrayAdapter
     */

    private val books: List<Book>
    private val inflater: LayoutInflater


    override fun getItem(position: Int): Book? {
        return books[position]
    }

    override fun getCount(): Int {
        return books.size
    }

    override fun getView(
        position: Int,
        convertView: View?,
        parent: ViewGroup
    ): View {
        val binding: BooksListRowBinding =
            DataBindingUtil.inflate(inflater, R.layout.books_list_row, parent, false)
        PicassoHelper.draw(
            context,
            binding.root.findViewById(R.id.book_image),
            books[position].imageLink
        )
        binding.book = books[position]
        return binding.root
    }

    init {
        inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        this.books = books
    }
}