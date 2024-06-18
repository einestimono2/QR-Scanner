package com.example.tranning_qr_scanner.core.base

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding
import com.example.tranning_qr_scanner.R
import com.example.tranning_qr_scanner.core.utils.TransitionType

abstract class BaseActivity<VB : ViewDataBinding> : AppCompatActivity() {
    protected lateinit var binding: VB
    abstract val transitionType: TransitionType

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /* Animation */
        if (Build.VERSION.SDK_INT >= 34 && transitionType != TransitionType.NONE) {
            /*when launching Activity B which gets started from Activity A*/
            this.overrideActivityTransition(
                OVERRIDE_TRANSITION_OPEN,
                getEnterAnim(),
                0
            )

            /*when finishing Activity B and back to Activity A*/
            this.overrideActivityTransition(
                OVERRIDE_TRANSITION_CLOSE,
                getExitAnim(),
                0
            )
        }

        /* Binding */
        binding = inflateLayout(layoutInflater)
        setContentView(binding.root)
    }

    override fun finish() {
        super.finish()

        if (Build.VERSION.SDK_INT < 34 && transitionType != TransitionType.NONE) {
            overridePendingTransition(getExitAnim(), 0)
        }
    }

    abstract fun inflateLayout(layoutInflater: LayoutInflater): VB

    protected fun <T : AppCompatActivity> navigate(target: Class<in T>, replace: Boolean = true) {
        startActivity(Intent(this, target))

        if (Build.VERSION.SDK_INT < 34 && transitionType != TransitionType.NONE) {
            overridePendingTransition(getEnterAnim(), 0)
        }

        if (replace) this.finish()
    }

    private fun getEnterAnim(): Int {
        return when (transitionType) {
            TransitionType.RTL -> R.anim.slide_rtl_in
            TransitionType.LTR -> R.anim.slide_ltr_in
            TransitionType.FADE -> R.anim.fade_in
            else -> 0
        }
    }

    private fun getExitAnim(): Int {
        return when (transitionType) {
            TransitionType.RTL -> R.anim.slide_rtl_out
            TransitionType.LTR -> R.anim.slide_ltr_out
            TransitionType.FADE -> R.anim.fade_out
            else -> 0
        }
    }

}
