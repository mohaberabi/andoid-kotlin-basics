package com.example.roomer

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [User::class], version = 1)
abstract class UserDb : RoomDatabase() {
    abstract fun usersDao(): UsersDAO

    companion object {
        @Volatile
        private var INSTANCE: UserDb? = null
        fun getInstance(context: Context): UserDb {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context, UserDb::class.java,
                        "usersDB"
                    )
                        .fallbackToDestructiveMigration().build()

                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}