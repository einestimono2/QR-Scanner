package com.example.tranning_qr_scanner.presentation.create_qr_detail.contents

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.example.tranning_qr_scanner.databinding.CreateQrDetailFormWhatsappBinding

class CreateQrWhatsapp : LinearLayout {
    private var binding: CreateQrDetailFormWhatsappBinding =
        CreateQrDetailFormWhatsappBinding.inflate(LayoutInflater.from(context), this, true)

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context, attrs, defStyleAttr
    )

}