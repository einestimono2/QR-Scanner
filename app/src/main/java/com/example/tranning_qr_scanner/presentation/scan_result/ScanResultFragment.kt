package com.example.tranning_qr_scanner.presentation.scan_result

import androidx.navigation.fragment.navArgs
import com.example.tranning_qr_scanner.databinding.ScanResultFragmentBinding
import com.example.tranning_qr_scanner.presentation.base.BaseFragment

class ScanResultFragment : BaseFragment<ScanResultFragmentBinding, Nothing>() {
    override fun inflateLayout() = ScanResultFragmentBinding.inflate(layoutInflater)

    private val args: ScanResultFragmentArgs by navArgs()

    override fun bind() {
        val barcode = args.barcode

        binding.scanResultFragContent.initContent(barcode)
    }
}