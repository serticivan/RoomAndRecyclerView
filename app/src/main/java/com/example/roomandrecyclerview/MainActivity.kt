package com.example.roomandrecyclerview

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomandrecyclerview.db.Book
import com.example.roomandrecyclerview.db.BookDatabase

import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    val booksDao = BookDatabase.getInstance().bookDao()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnAddBook.setOnClickListener {

            Intent(this, AddNewBookActivity::class.java).apply {
                startActivity(this)
            }
        }

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

    private fun displayBooks() {
        rvRecyclerViewBooks.adapter = BookAdapter(booksDao.getAllBooks())
        rvRecyclerViewBooks.layoutManager = LinearLayoutManager(this)
        rvRecyclerViewBooks.setHasFixedSize(true)

    }

    //    private fun displayBooks() {
//        booksDisplay.adapter = ArrayAdapter(
//            this,
//            android.R.layout.simple_list_item_1,
//            booksDao.getAllBooks()
//        )
//    }
//
    private fun destroy() {
        booksDao.nukeDb()
        displayBooks()
    }
//
//    private fun addBook() {
//        val index = Random().nextInt(100)
//        val book = Book(
//            0,
//            "Title$index",
//            "Author$index"
//        )
//        booksDao.insertBook(book)
//        displayBooks()
//    }
}
