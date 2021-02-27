package com.android.testapp.data

class BookImpl(
    /**
     * Implementation of Book
     *
     * @author me
     * @since 27.03.2021
     */
    //Book title
    private val title: String,
    //Book author
    private val author: String,
    //Year of issue
    private val year: String,
    //Book ISBN
    private val isbn: String,
    //Book description
    private val description: String,
    //Book cover
    private val imageLink: String
) : Book {
    override fun getTitle(): String {
        return title
    }

    override fun getImageLink(): String {
        return imageLink
    }

    override fun getDescription(): String {
        return description
    }

    override fun getAuthor(): String {
        return author
    }

    override fun getYear(): String {
        return year
    }

    override fun getISBN(): String {
        return isbn
    }

}