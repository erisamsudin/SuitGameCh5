package com.afiraz.ui.onboarding

import android.os.Bundle
import android.os.PersistableBundle
import androidx.core.content.res.TypedArrayUtils
import androidx.fragment.app.Fragment
import com.afiraz.suitgame.R
import com.github.appintro.AppIntro2
import com.github.appintro.AppIntroFragment
import com.github.appintro.model.SliderPage

class OnBoardingActivity : AppIntro2() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setupSliderFragment()
    }

    override fun onDonePressed(currentFragment: Fragment?) {
        super.onDonePressed(currentFragment)
        if (currentFragment is OnFinishNavigateListner){
            currentFragment.onFinishNavigateListener()
        }
    }

    private fun setupSliderFragment() {
        isSkipButtonEnabled = false
        addSlide(
            AppIntroFragment.createInstance(
                SliderPage(
                    title = getString(R.string.title_onboarding_one),
                    imageDrawable = R.drawable.ic_landing_page1,
                    titleTypefaceFontRes = R.font.gamesfont,
                    descriptionTypefaceFontRes = R.font.gamesfont,
                    titleColorRes = R.color.teal_200,
                )
            )
        )
        addSlide(
            AppIntroFragment.createInstance(
                SliderPage(
                    title = getString(R.string.title_onboarding_two),
                    imageDrawable = R.drawable.ic_landing_page2,
                    titleTypefaceFontRes = R.font.gamesfont,
                    descriptionTypefaceFontRes = R.font.gamesfont,
                    titleColorRes = R.color.teal_200
                )
            )
        )
        addSlide(EnterNameFragment())
    }
}

interface OnFinishNavigateListner{
        fun onFinishNavigateListener()
}