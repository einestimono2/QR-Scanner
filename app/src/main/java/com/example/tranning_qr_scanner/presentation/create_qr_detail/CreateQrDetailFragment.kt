package com.example.tranning_qr_scanner.presentation.create_qr_detail

import androidx.navigation.fragment.navArgs
import com.example.tranning_qr_scanner.databinding.CreateQrDetailFragmentBinding
import com.example.tranning_qr_scanner.presentation.base.BaseFragment
import kotlin.properties.Delegates

class CreateQrDetailFragment : BaseFragment<CreateQrDetailFragmentBinding, Nothing>() {
    override fun inflateLayout() = CreateQrDetailFragmentBinding.inflate(layoutInflater)

    private val args: CreateQrDetailFragmentArgs by navArgs()
    private var type by Delegates.notNull<Int>()

    override fun bind() {
        type = args.id

        binding.createQRFragAppBarTitle.setNavigationOnClickListener {
            pop()
        }
    }
}