package com.example.sevenminworkout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import android.widget.Toast
import com.example.sevenminworkout.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        binding?.historyButton?.setOnClickListener {
            goToHistory()
        }
        binding?.frameLayout?.setOnClickListener {
            goToExercise()
        }
        binding?.bmiButton?.setOnClickListener {
            goToBmi()
        }
    }


    private fun goToHistory() {
        val intent = Intent(this, HistoryActivity::class.java)
        startActivity(intent)
    }

    private fun goToBmi() {
        val exerciseIntent = Intent(this, Bmi::class.java)
        startActivity(exerciseIntent)
    }

    private fun goToExercise() {
        val exerciseIntent = Intent(this, ExerciseActivity::class.java)
        startActivity(exerciseIntent)
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}