package com.example.tranning_qr_scanner.features.onboarding.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import com.example.tranning_qr_scanner.MainActivity
import com.example.tranning_qr_scanner.core.base.BaseActivity
import com.example.tranning_qr_scanner.core.utils.TransitionType
import com.example.tranning_qr_scanner.databinding.SplashActivityBinding
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit


@SuppressLint("CustomSplashScreen")
class SplashActivity : BaseActivity<SplashActivityBinding>() {
    override fun inflateLayout(layoutInflater: LayoutInflater) = SplashActivityBinding.inflate(layoutInflater)
    override val transitionType = TransitionType.FADE

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Executors.newSingleThreadScheduledExecutor().schedule({
//            navigate(LanguageActivity::class.java)
            navigate(MainActivity::class.java)
        }, 2, TimeUnit.SECONDS)
    }

}