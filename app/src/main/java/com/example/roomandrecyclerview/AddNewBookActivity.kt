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

        btnCancelAddBook.setOnClickListener {
            finish()
        }

        btnSaveBook.setOnClickListener {

            if (etAddTitle.text.isNullOrEmpty() || etAddAuthor.text.isNullOrEmpty()) {
                when {
                    etAddTitle.text.isNullOrEmpty() -> {
                        etAddTitle.error = "Title required"
                        etAddTitle.requestFocus()
                    }
                    else -> {
                        etAddAuthor.error = "Author required"
                        etAddAuthor.requestFocus()
                    }
                }
            } else {

                val title = etAddTitle.text.toString()
                val author = etAddAuthor.text.toString()

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
