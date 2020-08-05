package com.sxcs.exebit2020.ui.activities

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sxcs.exebit2020.databinding.ActivityContactBinding

class ContactActivity : AppCompatActivity() {

    private lateinit var binding: ActivityContactBinding

    companion object {
        private const val PARAM_1 = "percent"
        private const val PARAM_2 = "disease"

        fun getIntent(context: Context, percentage: Double, disease: String) =
            Intent(context, ContactActivity::class.java).apply {
                putExtra(PARAM_1, percentage)
                putExtra(PARAM_2, disease)
            }
    }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContactBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val percentage = intent.getDoubleExtra(PARAM_1, 0.0)
        val disease = intent.getStringExtra(PARAM_2) ?: ""

        when {
            percentage < 45.0 -> {
                binding.textView.text = "You might have weak symptoms of $disease - $percentage%"
            }
            percentage in 45.0..75.0 -> {
                binding.textView.text = "You might have moderate symptoms of $disease - $percentage%.\n\nWe recommend you contact one of our psychiatrist."
                binding.textView4.text = "Name: Dr."
            }
            percentage in 75.0..100.0 -> {
                binding.textView.text = "You might have symptoms of $disease - $percentage%.\n\nWe recommend you contact one of our psychiatrist."
            }
        }

    }
}