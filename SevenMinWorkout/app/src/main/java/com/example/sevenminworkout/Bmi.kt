package com.example.sevenminworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.example.sevenminworkout.databinding.ActivityBmiBinding

class Bmi : AppCompatActivity() {
    private var binding: ActivityBmiBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        binding = ActivityBmiBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        setupToolBar()
        setubCalcButton()
    }

    private fun setupToolBar() {
        setSupportActionBar(binding?.toolbar)
        if (supportActionBar != null) {

            supportActionBar?.setDisplayHomeAsUpEnabled(true)

            supportActionBar?.title = "BMI Calculator"
        }
        binding?.toolbar?.setNavigationOnClickListener {
            onBackPressed()
        }
    }

    private fun validate(): Boolean {

        val heightEditText: EditText? = binding?.heightEditText
        val weightEditText: EditText? = binding?.weightEditText


        return heightEditText?.text.toString().isNotEmpty()
                && weightEditText?.text.toString()
            .isNotEmpty()
    }

    private fun setubCalcButton() {

        binding?.calcButton?.setOnClickListener {

            if (validate()) {

                val weight: Float = binding?.weightEditText?.text.toString().toFloat() / 100
                val height: Float = binding?.heightEditText?.text.toString().toFloat()
                val bmi = weight / (height * height)

                binding?.resultLinearLayout?.visibility = View.VISIBLE

                binding?.bmiResultText?.text = bmi.toString()


                binding?.bmiResultText?.text = bmiText(bmi)
            } else {
                Toast.makeText(
                    this,
                    "PLaese Enter Values",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

    }


    private fun bmiText(bmi: Float): String {

        if (bmi.compareTo(15f) <= 0) {
            return "under wight"
        } else if (bmi.compareTo(15f) > 0 && bmi.compareTo(18.5f) <= 0) {
            return "Normal"
        } else {
            return "over  Weight "
        }

    }
}