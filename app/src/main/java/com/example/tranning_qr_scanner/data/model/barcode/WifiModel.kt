package com.example.tranning_qr_scanner.data.model.barcode

import android.widget.Toast
import com.example.tranning_qr_scanner.R
import com.example.tranning_qr_scanner.core.utils.BarcodeFormat
import com.example.tranning_qr_scanner.core.utils.BarcodeType
import com.example.tranning_qr_scanner.core.utils.Constants.SCAN_RESULT_ACTION_MAX_SPAN_COUNT
import com.example.tranning_qr_scanner.core.utils.WifiSecurity
import com.example.tranning_qr_scanner.presentation.scan_result.ScanResultActionModel
import timber.log.Timber

data class WifiModel(
    override val format: BarcodeFormat,
    override val type: BarcodeType,
    override val timeStamp: String?,
    override val rawValue: String,
    val ssid: String?,
    val password: String?,
    val security: WifiSecurity?
) : BarcodeModel() {
    override val actions = listOf(
        ScanResultActionModel(R.drawable.ic_connect_wifi, R.string.connect) {
            Toast.makeText(it, "Feature 'Connect' is unavailable!", Toast.LENGTH_SHORT).show()
        },
        ScanResultActionModel(R.drawable.ic_copy_pass, R.string.copy_password) {
            Toast.makeText(it, "Feature 'Copy' is unavailable!", Toast.LENGTH_SHORT).show()
        },
        ScanResultActionModel(R.drawable.ic_share, R.string.share) {
            Toast.makeText(it, "Feature 'Share' is unavailable!", Toast.LENGTH_SHORT).show()
        }
    )

    override val spanCount: Int = actions.size.coerceAtMost(SCAN_RESULT_ACTION_MAX_SPAN_COUNT)
}
