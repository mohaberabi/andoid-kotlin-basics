package com.example.quizo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var startButton: Button
    private lateinit var nameEditText: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContentView(R.layout.activity_main)
        startButton = findViewById(R.id.startButton)
        nameEditText = findViewById(R.id.nameEditText)

        startButton.setOnClickListener {
            if (nameEditText.text.isNotEmpty()) {


                val quizIntent: Intent = Intent(this, QuizActivity::class.java)

                startActivity(quizIntent)
                finish()

            } else {
                showToast()
            }
        }
    }

    private fun showToast(text: String = "Please Enter Name ") {
        Toast.makeText(this, "Please Enter Name ", Toast.LENGTH_LONG).show()

    }
}