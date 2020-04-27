package com.example.roomandrecyclerview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.roomandrecyclerview.db.Book
import com.example.roomandrecyclerview.db.BookDatabase
import kotlinx.android.synthetic.main.activity_add_new_book.*

class AddNewBookActivity : AppCompatActivity() {

    val booksDao = BookDatabase.getInstance().bookDao()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_book)

        btnCancelSelectedBook.setOnClickListener {
            finish()
        }

        btnEditBook.setOnClickListener {

            if (etEditTitle.text.isNullOrEmpty() || etEditAuthor.text.isNullOrEmpty()) {
                when {
                    etEditTitle.text.isNullOrEmpty() -> {
                        etEditTitle.error = "Title required"
                        etEditTitle.requestFocus()
                    }
                    else -> {
                        etEditAuthor.error = "Author required"
                        etEditAuthor.requestFocus()
                    }
                }
            } else {

                val title = etEditTitle.text.toString()
                val author = etEditAuthor.text.toString()

                val book = Book(0, title, author)

                booksDao.insertBook(book)

                Log.d("TAG", "Book inserted title: $title, author: $author")

                Toast.makeText(this, "Book saved!!", Toast.LENGTH_SHORT).show()

                Intent(this, MainActivity::class.java).apply {
                    startActivity(this)
                }


            }


        }


    }
}
