package com.example.tranning_qr_scanner.presentation.home

import android.content.Intent
import android.net.Uri
import com.example.tranning_qr_scanner.databinding.NoPermissionFragmentBinding
import com.example.tranning_qr_scanner.presentation.base.BaseFragment
import android.provider.Settings
import com.example.tranning_qr_scanner.R
import com.example.tranning_qr_scanner.core.utils.TransitionType
import com.example.tranning_qr_scanner.core.utils.helper.PermissionHelper
import timber.log.Timber

class NoPermissionFragment : BaseFragment<NoPermissionFragmentBinding, Nothing>(){
    override fun inflateLayout() = NoPermissionFragmentBinding.inflate(layoutInflater)
    override val transitionType = TransitionType.NONE

    override fun bind() {
        binding.noPermissionDialogBtnGoSetting.setOnClickListener {
            val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
            val uri = Uri.fromParts("package", context?.packageName, null)
            intent.data = uri
            context?.startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()

        if (PermissionHelper.isCameraPermissionGranted(requireContext())) {
            navigate(R.id.scanQRFragment, true)
        }
    }
}