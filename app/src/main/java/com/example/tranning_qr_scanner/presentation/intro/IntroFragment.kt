package com.example.tranning_qr_scanner.presentation.intro

import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.viewpager2.widget.ViewPager2
import com.example.tranning_qr_scanner.R
import com.example.tranning_qr_scanner.core.utils.Constants.FIRST_TIME_LAUNCH_KEY
import com.example.tranning_qr_scanner.core.utils.extension.dp
import com.example.tranning_qr_scanner.core.utils.helper.AppCache
import com.example.tranning_qr_scanner.data.model.IntroModel
import com.example.tranning_qr_scanner.databinding.IntroFragmentBinding
import com.example.tranning_qr_scanner.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class IntroFragment : BaseFragment<IntroFragmentBinding, Nothing>() {
    override fun inflateLayout() = IntroFragmentBinding.inflate(layoutInflater)

    private lateinit var indicator: LinearLayout
    private lateinit var viewPager: ViewPager2
    private lateinit var btnNext: TextView
    private lateinit var btnSkip: TextView

    @Inject
    lateinit var cacheHelper: AppCache

    override fun bind() {
        viewPager = binding.introFragVp2
        indicator = binding.introFragIndicator
        btnNext = binding.introFragBtnNext
        btnSkip = binding.introFragBtnSkip

        /* Adapter */
        viewPager.adapter = IntroAdapter(IntroModel.LIST_INTRO)

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
        cacheHelper.setValue(FIRST_TIME_LAUNCH_KEY, "true")

        navigate(R.id.action_introFragment_to_homeFragment)
    }

    private fun initIndicator(size: Int) {
        for (i in 0 until size) {
            val dot = ImageView(requireContext()).apply {
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