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
