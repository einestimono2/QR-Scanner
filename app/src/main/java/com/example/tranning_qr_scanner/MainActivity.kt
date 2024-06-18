package com.example.tranning_qr_scanner

import android.os.Bundle
import android.view.LayoutInflater
import com.example.tranning_qr_scanner.core.base.BaseActivity
import com.example.tranning_qr_scanner.core.utils.TransitionType
import com.example.tranning_qr_scanner.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>() {
    override fun inflateLayout(layoutInflater: LayoutInflater) = ActivityMainBinding.inflate(layoutInflater)
    override val transitionType = TransitionType.FADE

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}