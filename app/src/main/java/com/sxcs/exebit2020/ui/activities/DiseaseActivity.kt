package com.sxcs.exebit2020.ui.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sxcs.exebit2020.R
import com.sxcs.exebit2020.databinding.ActivityDiseaseBinding

class DiseaseActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDiseaseBinding

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

        val questions = resources.getStringArray(when(disease) {
            "Depression" -> R.array.depression_questions
            "PSTD" -> R.array.pstd_questions
            "Scitz" -> R.array.schitz_questions
            "Anxiety" -> R.array.anxiety_questions
            else -> R.array.depression_questions
        })
    }
}