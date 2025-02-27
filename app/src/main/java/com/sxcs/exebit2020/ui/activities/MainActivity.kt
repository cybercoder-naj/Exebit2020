package com.sxcs.exebit2020.ui.activities

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.text.Html
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import androidx.viewpager.widget.ViewPager
import com.sxcs.exebit2020.R
import com.sxcs.exebit2020.databinding.ActivityMainBinding
import com.sxcs.exebit2020.ui.onboarding.OnBoardingItem
import com.sxcs.exebit2020.ui.onboarding.OnBoardingViewPagerAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var dots: Array<TextView>
    private var currentPage = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if(isFirstTime()) {
            val list = arrayListOf(
                OnBoardingItem(
                    "Welcome to ${getString(R.string.app_name)}",
                    "This mobile application will help you identify the problems you might be facing during the COVID - 19 pandemic.",
                    R.drawable.logo
                ),
                OnBoardingItem(
                    "Firstly, you need to answer a few of our questions.",
                    "This is going to be a short survey.",
                    R.drawable.survey
                ),
                OnBoardingItem(
                    "Then, we will tell you if you have symptoms of Depression, PTSD, Schizophrenia or Anxiety.",
                    "In case we believe that you have those, we will recommend a specialist, you may see.",
                    R.drawable.doctor
                )
            )

            with(binding) {
                viewPagerOnBoarding.adapter = OnBoardingViewPagerAdapter(this@MainActivity, list)
                viewPagerOnBoarding.addOnPageChangeListener(object :
                    ViewPager.OnPageChangeListener {
                    override fun onPageScrollStateChanged(state: Int) {

                    }

                    override fun onPageScrolled(
                        position: Int,
                        positionOffset: Float,
                        positionOffsetPixels: Int
                    ) {

                    }

                    override fun onPageSelected(position: Int) {
                        addDotsIndicator(position, list.size)
                        currentPage = position
                        when (position) {
                            0 -> {
                                binding.btnNext.isEnabled = true
                                binding.btnBack.visibility = View.GONE
                                binding.btnNext.text = getString(R.string.next)
                                binding.btnNext.setCompoundDrawables(
                                    null,
                                    null,
                                    getDrawable(R.drawable.ic_baseline_arrow_forward_24),
                                    null
                                )
                            }
                            dots.size - 1 -> {
                                binding.btnNext.isEnabled = true
                                binding.btnBack.isEnabled = true

                                binding.btnBack.visibility = View.VISIBLE

                                binding.btnNext.text = "Finish"
                                binding.btnNext.setCompoundDrawables(null, null, null, null)
                            }
                            else -> {
                                binding.btnNext.isEnabled = true
                                binding.btnBack.isEnabled = true
                                binding.btnNext.setCompoundDrawables(
                                    null,
                                    null,
                                    getDrawable(R.drawable.ic_baseline_arrow_forward_24),
                                    null
                                )
                                binding.btnBack.visibility = View.VISIBLE
                                binding.btnNext.text = getString(R.string.next)
                            }
                        }
                    }
                })

                binding.btnNext.setOnClickListener {
                    if (btnNext.text == "Next")
                        viewPagerOnBoarding.currentItem = currentPage + 1
                    else {
                        QuestionnaireActivity.getIntent(this@MainActivity).also {
                            startActivity(it)
                        }
                        setFirstTime()
                    }
                }
                binding.btnBack.setOnClickListener {
                    viewPagerOnBoarding.currentItem = currentPage - 1
                }
            }

            addDotsIndicator(0, list.size)
        } else {
            QuestionnaireActivity.getIntent(this).also {
                startActivity(it)
            }
        }
    }

    private fun addDotsIndicator(i: Int, size: Int) {
        dots = Array(size) { TextView(this) }
        binding.linearLayout.removeAllViews()

        dots.forEach {
            it.text = Html.fromHtml("&#8226;", 0)
            it.textSize = 35f
            it.setTextColor(Color.parseColor("#CCCCCC"))
            binding.linearLayout.addView(it)
        }

        if (dots.isNotEmpty())
            dots[i].setTextColor(Color.parseColor("#ffffff"))
    }

    private fun isFirstTime(): Boolean {
        val prefs = getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
        return prefs.getBoolean("isFirstTime", true)
    }

    private fun setFirstTime() {
        val prefs = getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
        prefs.edit()
            .putBoolean("isFirstTime", false)
            .apply()
    }
}