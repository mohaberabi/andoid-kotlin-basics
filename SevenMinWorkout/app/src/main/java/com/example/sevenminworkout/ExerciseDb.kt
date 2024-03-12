package com.example.sevenminworkout

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ExerciseModel::class], version = 1)
abstract class ExerciseDb : RoomDatabase() {
    abstract fun exerciseDao(): ExerciseDao

    companion object {
        @Volatile
        private var INSTANCE: ExerciseDb? = null
        fun getInstance(context: Context): ExerciseDb {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context, ExerciseDb::class.java,
                        "exerciseDb"
                    )
                        .fallbackToDestructiveMigration().build()

                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}