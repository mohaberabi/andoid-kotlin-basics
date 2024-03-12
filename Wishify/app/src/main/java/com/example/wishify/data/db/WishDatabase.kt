package com.example.wishify.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.wishify.data.model.WishItem

@Database(
    entities = [WishItem::class],
    version = 1,
)
abstract class WishDatabase : RoomDatabase() {
    abstract fun wishDao(): WishDao
}