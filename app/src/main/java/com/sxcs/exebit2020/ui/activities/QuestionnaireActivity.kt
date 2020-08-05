package com.sxcs.exebit2020.ui.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.sxcs.exebit2020.databinding.ActivityQuestionnaireBinding
import kotlinx.android.synthetic.main.layout_onboarding.*
import kotlin.system.measureNanoTime

class QuestionnaireActivity : AppCompatActivity() {

    private lateinit var binding: ActivityQuestionnaireBinding
    private var questionNumber = 1

    companion object {
        fun getIntent(context: Context) =
            Intent(context, QuestionnaireActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuestionnaireBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mentalQuestions()
    }

    private fun mentalQuestions() {
        setQuestions()
        var disease = "None"
        with(binding) {
            btnSubmit.setOnClickListener {
                if (!radYes.isChecked && !radNo.isChecked) {
                    Toast.makeText(
                        this@QuestionnaireActivity,
                        "You have to select Yes/No to continue",
                        Toast.LENGTH_LONG
                    ).show()
                } else {
                    if (questionNumber == 1) {
                        if (radYes.isChecked)
                            questionNumber = 2
                        else if (radNo.isChecked)
                            questionNumber = 3
                    } else if (questionNumber == 2) {
                        if (radYes.isChecked)
                            disease = "PTSD"
                        else if (radNo.isChecked)
                            disease = "Scitz"
                    } else if(questionNumber == 3) {
                        if (radYes.isChecked)
                            disease = "Depression"
                        else if (radNo.isChecked)
                            disease = "Anxiety"
                    }
                    if (disease != "None") {
                        DiseaseActivity.getIntent(this@QuestionnaireActivity, disease).also {
                            startActivity(it)
                        }
                    }
                }
                setQuestions()
            }
        }
    }

    private fun setQuestions() {
        binding.tvQuestion.text = when (questionNumber) {
            1 -> "Have you been having hallucinations or delusions?"
            2 -> "Have you been hallucinating about a real event that you witnessed?"
            3 -> "Have you been feeling sad?"
            else -> ""
        }
    }
}