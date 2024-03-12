package com.example.sevenminworkout

import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.provider.MediaStore.Audio.Media
import android.speech.tts.TextToSpeech
import android.text.Layout
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.sevenminworkout.databinding.ActivityExerciseBinding
import java.util.Locale

class ExerciseActivity : AppCompatActivity(), TextToSpeech.OnInitListener {

    private var binding: ActivityExerciseBinding? = null
    private lateinit var tts: TextToSpeech
    private var timer: CountDownTimer? = null
    private lateinit var mediaPlayer: MediaPlayer
    private var index = 0

    private lateinit var exerciseAdapter: ExerciseAdapter
    private lateinit var exercises: List<ExerciseModel>
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityExerciseBinding.inflate(layoutInflater)

        setContentView(binding?.root)

        exercises = Constants.defaultExerciseList()

        setupToolBar()
        setupExerciseRecyclerView()
        setupTextToSpeech()
        binding?.progressBar?.progress = 0
        setupCounter(onFinish = {
            binding?.progressBar?.max = 30
            binding?.progressBar?.progress = 300
            binding?.exerciseImage?.visibility = View.VISIBLE
            renderExercise(exercises[0].getName(), exercises[0].getImage())
            exercises[0].setSeelcted(true)
            startExercise()
        })


    }

    private fun setupToolBar() {
        setSupportActionBar(binding?.toolBar)
        if (supportActionBar != null) {

            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
        binding?.toolBar?.setNavigationOnClickListener {
            onBackPressed()
        }
    }


    private fun startExercise() {
        binding?.progressBar?.progress = 0
        binding?.exerciseImage?.visibility = View.VISIBLE
        setupCounter(
            onFinish = {
                if (index < exercises.size - 1) {
                    index++
                    startNextExercise()
                } else {
                    whenDone()
                }
            },
            millis = 30000, length = 30,
        )
        renderExercise(
            exercises[index].getName(),
            exercises[index].getImage()
        )
    }

    private fun startNextExercise() {
        binding?.exerciseImage?.visibility = View.VISIBLE
        setupCounter(
            onFinish = {
                startExercise()
            },
            millis = 30000, length = 30,
        )
        renderExercise(
            exercises[index].getName(),
            exercises[index].getImage()
        )

        exercises[index].setSeelcted(true)
        exerciseAdapter.notifyDataSetChanged()

    }

    private fun renderExercise(name: String, image: Int) {
        binding?.title?.text = name
        binding?.exerciseImage?.setImageResource(image)

        speech(name)
    }

    private fun setupCounter(
        millis: Long = 10000,
        onFinish: () -> Unit = {},
        length: Int = 10,
        incremental: Int = 0,
    ) {
        var inc = incremental
        timer = object : CountDownTimer(millis, 1000) {
            override fun onTick(p0: Long) {
                inc++
                binding?.progressBar?.progress = length - inc
                binding?.timerTextView?.text = (length - inc).toString()
            }

            override fun onFinish() {
                onFinish()
            }
        }.start()
    }


    private fun resetTimer() {
        if (timer != null) {
            timer!!.cancel()
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
        resetTimer()
        binding = null
        tts.stop()
        mediaPlayer.stop()
    }


    private fun setupTextToSpeech() {
        tts = TextToSpeech(this, this)

    }

    private fun setupExerciseRecyclerView() {
        exerciseAdapter = ExerciseAdapter(exercises)
        binding?.recViewExercise?.adapter = exerciseAdapter

        binding?.recViewExercise?.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        setSupportActionBar(binding?.toolBar)

    }


    private fun whenDone() {
        val intent = Intent(this, DoneActivity::class.java)
        finish()
        startActivity(intent)
    }

    private fun playSound() {
        try {
            val sound = Uri.parse("adnroid.resource.package.....")

            mediaPlayer = MediaPlayer.create(this, sound)
            mediaPlayer.isLooping = false
            mediaPlayer.start()
        } catch (e: Exception) {

            Toast.makeText(this@ExerciseActivity, e.toString(), Toast.LENGTH_SHORT).show()
        }
    }
}