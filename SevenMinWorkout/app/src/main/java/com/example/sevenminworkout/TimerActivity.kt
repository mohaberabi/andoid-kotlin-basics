package com.example.sevenminworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Toast
import com.example.sevenminworkout.databinding.ActivityTimerBinding

class TimerActivity : AppCompatActivity() {

    private var binding: ActivityTimerBinding? = null
    private var countDownTimer: CountDownTimer? = null


    private var timerDuration: Long = 60000

    private var pauseOffset: Long = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityTimerBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding?.root)
        binding?.timerValue?.text = (timerDuration / 1000).toString()

        binding?.start?.setOnClickListener {
            startTimer(pauseOffset)

        }
        binding?.pause?.setOnClickListener {
            pauseTimer()

        }
        binding?.reset?.setOnClickListener {
            reset()

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }


    private fun pauseTimer() {
        if (countDownTimer != null) {
            countDownTimer?.cancel()
        }
    }


    private fun reset() {
        if (countDownTimer != null) {
            countDownTimer?.cancel()
            binding?.timerValue?.text = "${timerDuration / 1000}"
            countDownTimer = null
            pauseOffset = 0
        }
    }

    private fun startTimer(offset: Long) {
        countDownTimer = object : CountDownTimer(
            timerDuration - pauseOffset,
            1000
        ) {
            override fun onTick(milliSecondsUntilFinished: Long) {

                pauseOffset = timerDuration - milliSecondsUntilFinished
                binding?.timerValue?.text = (milliSecondsUntilFinished / 1000).toString()
            }

            override fun onFinish() {
                Toast.makeText(this@TimerActivity, "Timer is done ", Toast.LENGTH_SHORT).show()
            }
        }.start()


    }
}