package com.example.sevenminworkout

import android.app.Application

class SevenMinutesApp : Application() {

    val exerciseDb by lazy {
        ExerciseDb.getInstance(this)
    }
}