package com.example.tranning_qr_scanner.presentation.splash

import androidx.lifecycle.lifecycleScope
import com.example.tranning_qr_scanner.R
import com.example.tranning_qr_scanner.core.utils.Constants.FIRST_TIME_LAUNCH_KEY
import com.example.tranning_qr_scanner.core.utils.TransitionType
import com.example.tranning_qr_scanner.core.utils.helper.AppCache
import com.example.tranning_qr_scanner.databinding.SplashFragmentBinding
import com.example.tranning_qr_scanner.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class SplashFragment : BaseFragment<SplashFragmentBinding, Nothing>() {
    @Inject
    lateinit var cacheHelper: AppCache

    override val transitionType = TransitionType.NONE

    override fun inflateLayout() = SplashFragmentBinding.inflate(layoutInflater)

    override fun bind() {
        lifecycleScope.launch {
            delay(2000)

            if (cacheHelper.readValue(FIRST_TIME_LAUNCH_KEY) == null) {
                navigate(R.id.action_splashFragment_to_languageFragment)
            } else {
                navigate(R.id.action_splashFragment_to_homeFragment)
            }
        }
    }
}