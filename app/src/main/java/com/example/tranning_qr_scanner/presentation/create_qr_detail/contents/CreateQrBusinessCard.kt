package com.example.tranning_qr_scanner.presentation.create_qr_detail.contents

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.findViewTreeViewModelStoreOwner
import androidx.lifecycle.get
import com.example.tranning_qr_scanner.databinding.CreateQrDetailFormBusinessCardBinding
import com.example.tranning_qr_scanner.presentation.create_qr_detail.CreateQrDetailViewModel
import timber.log.Timber

class CreateQrBusinessCard : LinearLayout {
    private var binding: CreateQrDetailFormBusinessCardBinding =
        CreateQrDetailFormBusinessCardBinding.inflate(LayoutInflater.from(context), this, true)

    private val viewModel by lazy {
        ViewModelProvider(findViewTreeViewModelStoreOwner()!!)[CreateQrDetailViewModel::class.java]
    }

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context, attrs, defStyleAttr
    )

    init {
        binding.createQrDetailBusinessCardMail.text
    }

    fun onQrCreated() {
        Timber.v("INIT BC")
        binding.createQrDetailBusinessCardMail.error = "NULL"
    }
}