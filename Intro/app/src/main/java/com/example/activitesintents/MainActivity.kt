package com.example.activitesintents

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn : Button = findViewById(R.id.nav)
        val fName : EditText = findViewById(R.id.edFirstName)
        val lName : EditText = findViewById(R.id.edLastName)

        btn.setOnClickListener{


            val fNameText : String = fName.text.toString()
            val lNameText  : String = lName.text.toString()

            val profile  = Intent(this,ProfileActivity::class.java)
            profile.putExtra("name",fNameText)
            profile.putExtra("lastName",lNameText)


            startActivity(profile)

        }
    }
}