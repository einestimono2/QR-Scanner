package com.example.tranning_qr_scanner.presentation.create_qr

import android.view.View
import com.example.tranning_qr_scanner.databinding.CreateQrFragmentBinding
import com.example.tranning_qr_scanner.presentation.base.BaseFragment

class CreateQRFragment : BaseFragment<CreateQrFragmentBinding, Nothing>() {
    override fun inflateLayout() = CreateQrFragmentBinding.inflate(layoutInflater)

    override fun bind() {
        binding.fragment = this
    }

    fun handleCreateQr(view: View) {
        val action = CreateQRFragmentDirections.actionCreateQRFragmentToCreateQrDetailFragment(view.id)
        navigate(action)
    }
}