package com.example.applycourotines

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private lateinit var button: Button

    private lateinit var dialog: Dialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button = findViewById(R.id.button)
        button.setOnClickListener {

            showDialog()
            lifecycleScope.launch {
                execute("Done")
            }
        }
    }


    private fun closeWhenDone() {
        dialog.dismiss()
    }

    private fun showDialog() {

        dialog = Dialog(this@MainActivity)

        dialog.setContentView(R.layout.loader)
        dialog.show()

    }

    private suspend fun execute(result: String) {
        withContext(Dispatchers.IO) {
            for (i in 1..100000) {
                Log.i("delay", " $i")
            }
            runOnUiThread {
                closeWhenDone()
                Toast.makeText(this@MainActivity, result, Toast.LENGTH_SHORT).show()

            }

        }

    }
}