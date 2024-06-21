package com.example.tranning_qr_scanner.presentation.language

import androidx.lifecycle.ViewModel

class LanguageViewModel : ViewModel() {
    lateinit var selectedLanguage: String

    fun setDefaultLanguage(code: String) {
        selectedLanguage = code
    }

    fun onLanguageChange(code: String) {
        selectedLanguage = code
    }
}