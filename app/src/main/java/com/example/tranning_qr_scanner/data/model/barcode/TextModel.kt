package com.example.tranning_qr_scanner.data.model.barcode

import com.example.tranning_qr_scanner.R
import com.example.tranning_qr_scanner.core.utils.BarcodeFormat
import com.example.tranning_qr_scanner.core.utils.BarcodeType
import com.example.tranning_qr_scanner.core.utils.Constants.SCAN_RESULT_ACTION_MAX_SPAN_COUNT
import com.example.tranning_qr_scanner.presentation.scan_result.ScanResultActionModel
import timber.log.Timber

data class TextModel(
    override val format: BarcodeFormat,
    override val type: BarcodeType,
    override val timeStamp: String?,
    override val rawValue: String,
    val text: String?,
) : BarcodeModel(){
    override val actions = listOf(
        ScanResultActionModel(R.drawable.ic_open_web, R.string.search_web) {
            Timber.v("search_web")
        },
        ScanResultActionModel(R.drawable.ic_copy, R.string.copy) {
            Timber.v("Share")
        },
        ScanResultActionModel(R.drawable.ic_share, R.string.share) {
            Timber.v("Share")
        }
    )

    override val spanCount: Int = actions.size.coerceAtMost(SCAN_RESULT_ACTION_MAX_SPAN_COUNT)
}
