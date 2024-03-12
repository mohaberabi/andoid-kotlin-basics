package com.example.sevenminworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sevenminworkout.databinding.ActivityRecyclerViewBinding

class RecyclerViewActivity : AppCompatActivity() {


    private var binding: ActivityRecyclerViewBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecyclerViewBinding.inflate(layoutInflater)
        setContentView(binding?.root)


        val taskAdapter = TaskAdapter(Constants.tasksList)

        binding?.recView?.adapter = taskAdapter
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}