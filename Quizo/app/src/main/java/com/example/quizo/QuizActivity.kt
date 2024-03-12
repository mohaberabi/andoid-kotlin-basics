package com.example.quizo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.quizo.data.Constants
import com.example.quizo.data.QuizModel

class QuizActivity : AppCompatActivity() {


    lateinit var qFlag: ImageView
    lateinit var submitButton: Button
    lateinit var qText: TextView
    lateinit var qProgressBar: ProgressBar
    lateinit var op1: TextView
    lateinit var op2: TextView
    lateinit var op3: TextView
    lateinit var op4: TextView
    lateinit var progressText: TextView
    lateinit var questions: ArrayList<QuizModel>
    var questionIndex: Int = 0
    var selectedOption: Int = 0
    lateinit var currentQuestion: QuizModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)
        qText = findViewById(R.id.questionText)
        qFlag = findViewById(R.id.questionFlag)
        qProgressBar = findViewById(R.id.progrssBar)
        progressText = findViewById(R.id.progresText)
        op1 = findViewById(R.id.op1)
        op2 = findViewById(R.id.op2)
        op3 = findViewById(R.id.op3)
        op4 = findViewById(R.id.op4)

        submitButton = findViewById(R.id.submitBtn)
        questions = Constants.getQuestions()
        initQuestion()
        submitButton.setOnClickListener {


            if (selectedOption == 0) {
                questionIndex++
                when {
                    questionIndex < questions.size -> {
                        initQuestion()
                    }
                }
            } else {
                val question = questions.get(questionIndex)
                if (question.correct != selectedOption - 1) {
                    renderAnswer(answer = selectedOption - 1, R.drawable.wrong_holo)
                } else {
                    renderAnswer(answer = selectedOption - 1, R.drawable.correct_holo)

                }
            }
        }

    }

    private fun initQuestion() {
        currentQuestion = questions[questionIndex]
        qText.text = currentQuestion.question
        op1.text = currentQuestion.optionOne
        op2.text = currentQuestion.optionTwo
        op3.text = currentQuestion.optionThree
        op4.text = currentQuestion.optionFour
        progressText.text = "${questionIndex + 1}/${questions.size}"
        qFlag.setImageResource(currentQuestion.image)

    }

    private fun renderAnswer(answer: Int, drView: Int) {
        when (answer) {
            1 -> op1.background = ContextCompat.getDrawable(this, drView)
            2 -> op2.background = ContextCompat.getDrawable(this, drView)
            3 -> op3.background = ContextCompat.getDrawable(this, drView)
            else -> op4.background = ContextCompat.getDrawable(this, drView)
        }
    }
}