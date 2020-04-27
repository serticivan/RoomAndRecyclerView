package com.example.roomandrecyclerview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.roomandrecyclerview.db.BookDatabase
import kotlinx.android.synthetic.main.activity_selected_book.*

class SelectedBookActivity : AppCompatActivity() {

    private val booksDao = BookDatabase.getInstance().bookDao()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_selected_book)

        val actionbar = supportActionBar
        actionbar!!.title = "Edit book"
        actionbar.setDisplayHomeAsUpEnabled(true)
        actionbar.setDisplayShowHomeEnabled(true)

        btnCancelSelectedBook.setOnClickListener {
            finish()
        }

        val title = intent.getStringExtra("EXTRA_TITLE")
        val author = intent.getStringExtra("EXTRA_AUTHOR")
        val id = intent.getIntExtra("EXTRA_ID", 0)

        etEditTitle.setText(title)
        etEditAuthor.setText(author)

        btnUpdateSelectedBook.setOnClickListener {

            if (etEditTitle.text.isNullOrEmpty() || etEditAuthor.text.isNullOrEmpty()) {
                when {
                    etEditTitle.text.isNullOrEmpty() -> {
                        etEditTitle.error = "Tittle required"
                        etEditTitle.requestFocus()
                    }
                    else -> {
                        etEditAuthor.error = "Author required"
                        etEditAuthor.requestFocus()
                    }
                }
            } else {
                val updatedTitle = etEditTitle.text.toString()
                val updatedAuthor = etEditAuthor.text.toString()

                if (updatedTitle == title && updatedAuthor == author) {
                    Toast.makeText(this, "There is no change", Toast.LENGTH_SHORT).show()
                    finish()
                } else {
                    booksDao.updateBook(id, updatedTitle, updatedAuthor)
                    Toast.makeText(
                        this,
                        "Book updated from $title to $updatedTitle and $author to $updatedAuthor on $id",
                        Toast.LENGTH_SHORT
                    ).show()
                    Intent(this, MainActivity::class.java).apply {
                        startActivity(this)
                    }
                }


            }
        }

        btnDeleteSelectedBook.setOnClickListener {
            booksDao.deleteByUserId(id)
            Toast.makeText(this, "Book deleted $title, $id", Toast.LENGTH_SHORT).show()
            Intent(this, MainActivity::class.java).apply {
                startActivity(this)
            }
        }

    }


    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
