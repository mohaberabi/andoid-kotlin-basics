package com.example.sevenminworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sevenminworkout.databinding.ActivityDoneBinding

class DoneActivity : AppCompatActivity() {
    private var binding: ActivityDoneBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDoneBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        setupToolBar()

        binding?.doneButton?.setOnClickListener {


        }
    }


    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    private fun setupToolBar() {
        if (supportActionBar != null) {

            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
        binding?.toolBar?.setNavigationOnClickListener {
            onBackPressed()
        }
    }

}