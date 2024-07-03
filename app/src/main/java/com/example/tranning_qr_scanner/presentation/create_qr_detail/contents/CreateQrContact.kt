package com.example.tranning_qr_scanner.presentation.create_qr_detail.contents

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.example.tranning_qr_scanner.databinding.CreateQrDetailFormContactBinding

class CreateQrContact : LinearLayout {
    private var binding: CreateQrDetailFormContactBinding =
        CreateQrDetailFormContactBinding.inflate(LayoutInflater.from(context), this, true)

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context, attrs, defStyleAttr
    )

}