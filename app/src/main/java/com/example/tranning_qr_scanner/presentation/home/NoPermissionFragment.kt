package com.example.tranning_qr_scanner.presentation.home

import com.example.tranning_qr_scanner.R
import com.example.tranning_qr_scanner.core.utils.TransitionType
import com.example.tranning_qr_scanner.core.utils.Utilities
import com.example.tranning_qr_scanner.core.utils.helper.PermissionHelper
import com.example.tranning_qr_scanner.databinding.NoPermissionFragmentBinding
import com.example.tranning_qr_scanner.presentation.base.BaseFragment

class NoPermissionFragment : BaseFragment<NoPermissionFragmentBinding, Nothing>() {
    override fun inflateLayout() = NoPermissionFragmentBinding.inflate(layoutInflater)
    override val transitionType = TransitionType.NONE

    override fun bind() {
        binding.noPermissionDialogBtnGoSetting.setOnClickListener {
            Utilities.openAppSettings(context)
        }
    }

    override fun onResume() {
        super.onResume()

        if (PermissionHelper.isCameraPermissionGranted(requireContext())) {
            navigate(R.id.scanQRFragment, true)
        }
    }
}