package com.example.sevenminworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sevenminworkout.databinding.ActivityExerciseBinding
import com.example.sevenminworkout.databinding.ActivityHistoryBinding

class HistoryActivity : AppCompatActivity() {


    private var binding: ActivityHistoryBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        val dao = (application as SevenMinutesApp).exerciseDb.exerciseDao()
    }


    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}