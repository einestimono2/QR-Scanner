package com.example.tranning_qr_scanner.features.onboarding.view

import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.tranning_qr_scanner.core.base.BaseActivity
import com.example.tranning_qr_scanner.databinding.LanguageActivityBinding
import com.example.tranning_qr_scanner.core.extensions.dp
import com.example.tranning_qr_scanner.core.extensions.shortSnackbar
import com.example.tranning_qr_scanner.features.onboarding.adapter.LanguageAdapter
import com.example.tranning_qr_scanner.features.onboarding.model.LanguageModel
import com.example.tranning_qr_scanner.features.onboarding.viewmodel.LanguageViewModel
import com.example.tranning_qr_scanner.core.utils.RecyclerViewItemSpacing
import com.example.tranning_qr_scanner.core.utils.TransitionType


class LanguageActivity : BaseActivity<LanguageActivityBinding>() {
    private val viewModel: LanguageViewModel by viewModels()

    override fun inflateLayout(layoutInflater: LayoutInflater) = LanguageActivityBinding.inflate(layoutInflater)
    override val transitionType = TransitionType.FADE

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /* */
        viewModel.setDefaultLanguage(LanguageModel.LANGUAGES.first().code)

        /* Recycler View */
        val rv = binding.languageActRv
        rv.layoutManager = GridLayoutManager(this, 2)
        rv.addItemDecoration(RecyclerViewItemSpacing(12.dp))

        rv.adapter = LanguageAdapter(languages = LanguageModel.LANGUAGES, this, viewModel)

        /* Nav button */
        binding.languageActToolbarBtn.setOnClickListener {
            navigate(IntroActivity::class.java)
        }
    }
}