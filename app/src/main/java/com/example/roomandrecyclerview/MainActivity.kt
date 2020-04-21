package com.example.roomandrecyclerview

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomandrecyclerview.db.Book
import com.example.roomandrecyclerview.db.BookDatabase

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.book_row.*
import kotlinx.android.synthetic.main.book_row.view.*
import java.util.*

class MainActivity : AppCompatActivity() {

    private val booksDao = BookDatabase.getInstance().bookDao()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        displayBooks()

        btnAddBook.setOnClickListener {
            startAddNewBookActivity()
        }

    }

    private fun startAddNewBookActivity(){
        Intent(this, AddNewBookActivity::class.java).apply {
            startActivity(this)
        }
    }

    private fun displayBooks() {
        rvRecyclerViewBooks.adapter = BookAdapter(booksDao.getAllBooks())
        rvRecyclerViewBooks.layoutManager = LinearLayoutManager(this)
        rvRecyclerViewBooks.setHasFixedSize(true)

    }

    private fun destroy() {
        booksDao.nukeDb()
        displayBooks()
    }

    private fun generateDummyList(size: Int): List<Book> {

        val listOfBooks = ArrayList<Book>()

        for (i in 0 until size) {

            val book = Book(i, "title $i", "author $i")
            listOfBooks += book

        }

        return listOfBooks

    }

}
