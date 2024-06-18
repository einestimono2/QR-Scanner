package com.example.tranning_qr_scanner.features.onboarding.view

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.viewpager2.widget.ViewPager2
import com.example.tranning_qr_scanner.MainActivity
import com.example.tranning_qr_scanner.R
import com.example.tranning_qr_scanner.core.base.BaseActivity
import com.example.tranning_qr_scanner.databinding.IntroActivityBinding
import com.example.tranning_qr_scanner.core.extensions.dp
import com.example.tranning_qr_scanner.core.utils.TransitionType
import com.example.tranning_qr_scanner.features.onboarding.adapter.IntroAdapter
import com.example.tranning_qr_scanner.features.onboarding.model.IntroModel

class IntroActivity : BaseActivity<IntroActivityBinding>() {

    override fun inflateLayout(layoutInflater: LayoutInflater) = IntroActivityBinding.inflate(layoutInflater)
    override val transitionType = TransitionType.FADE

    private lateinit var indicator: LinearLayout
    private lateinit var viewPager: ViewPager2
    private lateinit var btnNext: TextView
    private lateinit var btnSkip: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewPager = binding.introActVp2
        indicator = binding.introActIndicator
        btnNext = binding.introActBtnNext
        btnSkip = binding.introActBtnSkip

        /* Adapter */
        viewPager.adapter = IntroAdapter(IntroModel.LIST_INTRO, this)

        /* Indicator */
        initIndicator(IntroModel.LIST_INTRO.size)

        /* Events */
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                updateIndicator(position)
            }
        })

        btnNext.setOnClickListener {
            handleNext()
        }

        btnSkip.setOnClickListener {
            handleFinish()
        }
    }

    private fun handleNext() {
        val idx = viewPager.currentItem

        if (idx < IntroModel.LIST_INTRO.size - 1) {
            viewPager.setCurrentItem(idx + 1, true)

            updateBtnNextText(idx + 1)
        } else {
            handleFinish()
        }
    }

    private fun handleFinish() {
        navigate(MainActivity::class.java)
    }

    private fun initIndicator(size: Int) {
        for (i in 0 until size) {
            val dot = ImageView(this).apply {
                /* State */
                isSelected = i == 0
                setImageResource(R.drawable.indicator_selector)

                /* Size & Margin (-2 ~ WRAP_CONTENT) */
                layoutParams = LinearLayout.LayoutParams(-2, -2).apply {
                    setMargins(0, 0, 8.dp, 0)
                }

                /* Event */
                setOnClickListener {
                    if (viewPager.currentItem != i) {
                        viewPager.setCurrentItem(i, true)
                    }
                }
            }

            indicator.addView(dot)
        }
    }

    private fun updateIndicator(idx: Int) {
        for (i in 0 until indicator.childCount) {
            with(indicator.getChildAt(i) as ImageView) {
                isSelected = i == idx
                requestLayout() // Render lại state
            }
        }

        updateBtnNextText(idx)
    }

    private fun updateBtnNextText(idx: Int) {
        btnNext.setText(if (idx == IntroModel.LIST_INTRO.size - 1) R.string.start else R.string.next)
    }
}