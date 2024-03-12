package com.example.sevenminworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import com.example.sevenminworkout.databinding.ActivityTextToSpeechBinding
import java.util.Locale

class TextToSpeechActivity : AppCompatActivity(), TextToSpeech.OnInitListener {

    private var binding: ActivityTextToSpeechBinding? = null

    private lateinit var tts: TextToSpeech
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTextToSpeechBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        tts = TextToSpeech(this, this)
        binding?.button?.setOnClickListener {


            if (binding?.editText?.text!!.isNotEmpty()) {

                speech(binding?.editText?.text.toString())
            } else {
                Toast.makeText(this, "Please neter the data", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onInit(status: Int) {

        if (status == TextToSpeech.SUCCESS) {
            val result = tts.setLanguage(Locale.US)

            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {

                Log.e("tts", "THE LANG IS NOT SUPPORTED")
            }
        }

    }


    private fun speech(text: String) {
        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null, "")
    }

    override fun onDestroy() {
        super.onDestroy()
        tts.stop()
        binding = null

    }
}