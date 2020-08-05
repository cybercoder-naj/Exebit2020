package com.sxcs.exebit2020.ui.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.sxcs.exebit2020.R
import com.sxcs.exebit2020.databinding.ActivityQuestionsBinding

class QuestionsActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityQuestionsBinding

    companion object {
        fun getIntent(context: Context) = Intent(context, QuestionsActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuestionsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            cardViewMental.setOnClickListener(this@QuestionsActivity)
            cardViewPhysical.setOnClickListener(this@QuestionsActivity)
        }
    }

    override fun onClick(v: View?) {
        v?.let { view ->
            val action =
                when (view.id) {
                    R.id.cardView_mental -> "Mental"
                    R.id.cardView_physical -> "Physical"
                    else -> ""
                }

            QuestionnaireActivity.getIntent(this, action).also {
                startActivity(it)
            }
        }
    }
}