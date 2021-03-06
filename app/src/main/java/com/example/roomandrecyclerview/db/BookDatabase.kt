package com.example.roomandrecyclerview.db

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(version = 1, entities = arrayOf(Book::class))
abstract class BookDatabase : RoomDatabase() {

    abstract fun bookDao(): BookDao

    companion object {

        private const val NAME = "book_database"
        private var INSTANCE: BookDatabase? = null

        fun getInstance(): BookDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    MyApplicationContext.ApplicationContext,
                    BookDatabase::class.java,
                    NAME
                )
                    .allowMainThreadQueries()
                    .build()
            }
            return INSTANCE as BookDatabase
        }

    }
}
