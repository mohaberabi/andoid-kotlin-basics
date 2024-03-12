package com.example.wishify.app

import android.app.Application


class Wishify : Application() {
    override fun onCreate() {
        super.onCreate()
        Graph.provide(this)
    }
}