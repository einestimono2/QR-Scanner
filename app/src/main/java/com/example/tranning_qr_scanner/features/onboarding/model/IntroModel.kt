package com.example.tranning_qr_scanner.features.onboarding.model

import com.example.tranning_qr_scanner.R

data class IntroModel(val title: Int, val content: Int, val image: Int) {
    companion object {
        val LIST_INTRO = listOf(
            IntroModel(R.string.intro_activity_title1, R.string.intro_activity_content1, R.drawable.img_intro1),
            IntroModel(R.string.intro_activity_title2, R.string.intro_activity_content2, R.drawable.img_intro2),
            IntroModel(R.string.intro_activity_title3, R.string.intro_activity_content3, R.drawable.img_intro3),
        )
    }
}