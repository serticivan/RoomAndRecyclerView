package com.example.roomandrecyclerview

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter

import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    val booksDao = BookDatabase.getInstance().bookDao()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnAddBook.setOnClickListener {
            addBook()
        }

        btnDestroy.setOnClickListener {
            destroy()
        }

        displayBooks()
    }

    private fun displayBooks() {
        booksDisplay.adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            booksDao.getAllBooks()
        )
    }

    private fun destroy() {
        booksDao.nukeDb()
        displayBooks()
    }

    private fun addBook() {
        val index = Random().nextInt(100)
        val book = Book(0, "Title$index", "Author$index")
        booksDao.insertBook(book)
        displayBooks()
    }
}
