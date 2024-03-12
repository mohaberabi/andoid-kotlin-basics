package com.example.roomer

import android.app.Application

class UsersApp : Application() {

    val db by lazy {
        UserDb.getInstance(this)
    }
}