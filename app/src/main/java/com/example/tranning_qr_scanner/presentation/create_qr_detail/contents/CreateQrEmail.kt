package com.example.tranning_qr_scanner.presentation.create_qr_detail.contents

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.findViewTreeViewModelStoreOwner
import com.example.tranning_qr_scanner.core.utils.BarcodeFormat
import com.example.tranning_qr_scanner.core.utils.BarcodeType
import com.example.tranning_qr_scanner.data.model.barcode.EmailModel
import com.example.tranning_qr_scanner.databinding.CreateQrDetailFormEmailBinding
import com.example.tranning_qr_scanner.presentation.create_qr_detail.CreateQrDetailViewModel

class CreateQrEmail : LinearLayout {
    private var binding: CreateQrDetailFormEmailBinding =
        CreateQrDetailFormEmailBinding.inflate(LayoutInflater.from(context), this, true)

    private val viewModel by lazy {
        ViewModelProvider(findViewTreeViewModelStoreOwner()!!)[CreateQrDetailViewModel::class.java]
    }

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context, attrs, defStyleAttr
    )

    init {
        val controlButtonEnabledWhenTextChanged = { _: CharSequence?, _: Int, _: Int, _: Int ->
            viewModel.canCreate.postValue(areAllFieldsFilled())
        }

        binding.createQrDetailFragmentFormEmailEmail.addTextChangedListener(
            onTextChanged = controlButtonEnabledWhenTextChanged
        )

        binding.createQrDetailFragmentFormEmailSubject.addTextChangedListener(
            onTextChanged = controlButtonEnabledWhenTextChanged
        )
    }

    private fun areAllFieldsFilled(): Boolean {
        return binding.createQrDetailFragmentFormEmailEmail.text.isNotBlank() && binding.createQrDetailFragmentFormEmailSubject.text.isNotBlank()
    }

    fun handleCreateQr() = EmailModel(
        BarcodeFormat.QR_CODE,
        BarcodeType.EMAIL,
        null,
        binding.createQrDetailFragmentFormEmailEmail.text.toString(),
        binding.createQrDetailFragmentFormEmailEmail.text.toString(),
        binding.createQrDetailFragmentFormEmailSubject.text.toString(),
        binding.createQrDetailFragmentFormEmailMessage.text.toString(),
        null
    )
}