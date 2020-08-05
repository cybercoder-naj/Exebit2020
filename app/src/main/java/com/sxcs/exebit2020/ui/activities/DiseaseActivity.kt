package com.sxcs.exebit2020.ui.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sxcs.exebit2020.R
import com.sxcs.exebit2020.databinding.ActivityDiseaseBinding

class DiseaseActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDiseaseBinding
    private var qNumber = 0
    private lateinit var questions: Array<out String>
    private var score = 0

    companion object {
        private const val PARAM_1 = "disease"
        fun getIntent(context: Context, disease: String) =
            Intent(context, DiseaseActivity::class.java).apply {
                putExtra(PARAM_1, disease)
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDiseaseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val disease = intent.getStringExtra(PARAM_1)

        questions = resources.getStringArray(
            when (disease) {
                "Depression" -> R.array.depression_q
                "PSTD" -> R.array.ptsd_q
                "Scitz" -> R.array.schitz_q
                "Anxiety" -> R.array.anxeity_q
                else -> R.array.depression_q
            }
        )

        askQuestion()
        binding.btnSubmit.setOnClickListener {
            score += binding.seekBar2.progress + 1
            qNumber++
            if (qNumber < questions.size)
                askQuestion()
            else

        }
    }

    private fun askQuestion() {
        binding.tvIntro.text = "Question ${qNumber + 1}"
        binding.tvQuestion.text = questions[qNumber]
    }
}