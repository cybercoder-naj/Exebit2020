package com.sxcs.exebit2020.ui.onboarding

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.viewpager.widget.PagerAdapter
import com.sxcs.exebit2020.R
import com.sxcs.exebit2020.databinding.LayoutOnboardingBinding

class OnBoardingViewPagerAdapter(
    private val context: Context,
    private val list: List<OnBoardingItem>
) : PagerAdapter() {

    override fun isViewFromObject(view: View, `object`: Any) = (`object` as ConstraintLayout) == view

    override fun getCount() = list.size

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val binding = LayoutOnboardingBinding.inflate(inflater, container, false)

        with(binding) {
            tvTitle.text = list[position].title
            tvDescription.text = list[position].description
            imageIntro.setImageResource(list[position].imageResId)
        }

        container.addView(binding.root)
        return binding.root
    }
}