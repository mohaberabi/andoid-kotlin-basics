package com.example.wishify.app

import android.content.Context
import androidx.room.Room
import com.example.wishify.data.db.WishDatabase
import com.example.wishify.data.repository.WishRepository

object Graph {
    private lateinit var database: WishDatabase
    lateinit var wishRepository: WishRepository


    fun provide(context: Context) {
        database = Room.databaseBuilder(
            context,
            WishDatabase::class.java, "wish"
        ).build()
        val wishDao = database.wishDao()
        wishRepository = WishRepository(wishDao)
    }
}