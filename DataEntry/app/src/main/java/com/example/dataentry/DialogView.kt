package com.example.dataentry

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.Window

class ContactDialog(context: Context) : Dialog(context) {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.app_dialog)


    }
}