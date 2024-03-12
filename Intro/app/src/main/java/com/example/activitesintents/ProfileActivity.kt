package com.example.activitesintents

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val name  = intent.getStringExtra("name")
        val lastName  = intent.getStringExtra("lastName")
         val textView : TextView  = findViewById(R.id.name)
        textView.text= "$name $lastName"

    }
}