package com.android.testapp.ui.main.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ListView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.android.testapp.R
import com.android.testapp.data.Book
import com.android.testapp.databinding.BooksListFragmentBinding
import com.android.testapp.http.BookService
import com.android.testapp.ui.main.PageViewModel

class BookListFragment : Fragment(), AdapterView.OnItemClickListener{

    /**
     * Getting access to booklistModel
     */
    private val bookListModel by lazy { ViewModelProviders.of(this).get(PageViewModel::class.java) }

    /**
     * RootView link
     */
    private lateinit var rootView: View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: BooksListFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.books_list_fragment, container,false)

        rootView = binding.root

        val bookList = rootView.findViewById<ListView>(R.id.book_list)

        bookListModel.getBookList().observe(this, Observer {
            it?.let {
                bookList.adapter = BookListAdapter(context!!, bookList.id,
                    bookListModel.getBookList().value!!
                )
            }
        })
        bookList.setOnItemClickListener(this)

        return rootView
    }

    /**
     * Call a new infoPage activity with explicit Intent
     * All necessary constant data will be transferred as intent extras
     */
    override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
       val book : Book? = bookListModel.booklist.value?.get(p2)
        if (book != null) {
            val intent = Intent(context, BookInfoPageActivity::class.java).apply {
                    putExtra(BookService.AUTHOR_KEY, book.author)
                    putExtra(BookService.TITLE_KEY, book.title)
                    putExtra(BookService.DESCRIPTION_KEY, book.description)
                    putExtra(BookService.ISBN_KEY, book.isbn)
                    putExtra(BookService.YEAR_KEY, book.year)
                    putExtra(BookService.IMAGE_KEY, book.imageLink)
                    System.out.println("Book" + book.getAuthor() + book.getDescription());
            }
            startActivity(intent)
        };
    }


}