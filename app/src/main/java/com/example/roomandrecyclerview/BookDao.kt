package com.example.roomandrecyclerview

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface BookDao {

    @Insert
    fun insertBook(book: Book)

    @Delete
    fun deleteBook(book: Book)

    @Query("SELECT * FROM books")
    fun getAllBooks(): List<Book>

    @Query("SELECT * FROM books WHERE author = :author")
    fun getAllBooksByAuthor(author: String): List<Book>

    @Query("DELETE FROM books")
    fun nukeDb()

}