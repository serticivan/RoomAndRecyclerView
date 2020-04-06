package com.example.roomandrecyclerview.db

import android.app.Application
import android.content.Context

class MyApplicationContex : Application() {
    companion object {
        lateinit var ApplicationContext: Context
            private set
    }
    override fun onCreate() {
        super.onCreate()
        ApplicationContext = this
    }
}
