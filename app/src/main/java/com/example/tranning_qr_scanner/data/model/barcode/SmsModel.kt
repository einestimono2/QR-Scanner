package com.example.tranning_qr_scanner.data.model.barcode

import android.widget.Toast
import com.example.tranning_qr_scanner.R
import com.example.tranning_qr_scanner.core.utils.BarcodeFormat
import com.example.tranning_qr_scanner.core.utils.BarcodeType
import com.example.tranning_qr_scanner.core.utils.Constants.SCAN_RESULT_ACTION_MAX_SPAN_COUNT
import com.example.tranning_qr_scanner.presentation.scan_result.ScanResultActionModel
import timber.log.Timber

data class SmsModel(
    override val format: BarcodeFormat,
    override val type: BarcodeType,
    override val timeStamp: String?,
    override val rawValue: String,
    val number: String?,
    val message: String?
) : BarcodeModel(){
    override val actions = listOf(
        ScanResultActionModel(R.drawable.ic_send_sms, R.string.send_sms) {
            Toast.makeText(it, "Feature 'Send' is unavailable!", Toast.LENGTH_SHORT).show()
        },
        ScanResultActionModel(R.drawable.ic_copy, R.string.copy) {
            Timber.v("Copy")
        }
    )

    override val spanCount: Int = actions.size.coerceAtMost(SCAN_RESULT_ACTION_MAX_SPAN_COUNT)
}
