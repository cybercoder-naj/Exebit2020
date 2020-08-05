package com.sxcs.exebit2020.ui.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sxcs.exebit2020.R

class QuestionnaireActivity: AppCompatActivity() {



    companion object {
        private const val PARAM_1 = "action"

        fun getIntent(context: Context, action: String) = Intent(context, QuestionnaireActivity::class.java).apply {
            putExtra(PARAM_1, action)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val action = intent.getStringExtra(PARAM_1)

        if (action == "Mental")
            mentalQuestions()
        else if (action == "Physical")
            physicalQuestions()
    }

    private fun physicalQuestions() {

    }

    private fun mentalQuestions() {

    }
}