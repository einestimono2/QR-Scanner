package com.example.tranning_qr_scanner.features.onboarding.viewmodel

import androidx.lifecycle.ViewModel
import com.example.tranning_qr_scanner.features.onboarding.model.LanguageModel

class LanguageViewModel : ViewModel() {
    lateinit var selectedLanguage: String

    fun setDefaultLanguage(code: String) {
        selectedLanguage = code
    }

    fun onLanguageChange(code: String) {
        selectedLanguage = code
    }
}