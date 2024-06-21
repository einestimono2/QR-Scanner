package com.example.tranning_qr_scanner.core.ui

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.example.tranning_qr_scanner.R
import com.example.tranning_qr_scanner.core.utils.extension.dp
import com.example.tranning_qr_scanner.databinding.AdsCardBinding

class AdsCard : LinearLayout {
    private var title: String = ""
    private var description: String = ""
    private var image: Int = -1
    private var type: Int = 0

    constructor(context: Context) : super(context) {
        bindView()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initAttributes(attrs)
        bindView()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        initAttributes(attrs)
        bindView()
    }

    private fun initAttributes(attrs: AttributeSet?) {
        val data = context.obtainStyledAttributes(attrs, R.styleable.AdsCard)

        title = data.getString(R.styleable.AdsCard_title).toString()
        description = data.getString(R.styleable.AdsCard_description).toString()
        image = data.getResourceId(R.styleable.AdsCard_image, -1)
        type = data.getInt(R.styleable.AdsCard_type, 0)

        data.recycle()
    }

    private fun bindView() {
        with(AdsCardBinding.inflate(LayoutInflater.from(this.context), this, true)) {
            root.orientation = type

            adsCardTitle.text = title
            adsCardDescription.text = description
            adsCardImage.setImageResource(image)

            /* HORIZONTAL */
            if (type == 0) {
                adsCardImage.layoutParams = LayoutParams(
                    LayoutParams.MATCH_PARENT,
                    LayoutParams.MATCH_PARENT
                ).apply {
                    setMargins(0, 0, 8.dp, 0)
                    weight = 1f
                }

                adsCardDescription.layoutParams = LayoutParams(
                    LayoutParams.WRAP_CONTENT,
                    LayoutParams.WRAP_CONTENT
                ).apply {
                    weight = 1f
                }
            }
        }
    }
}