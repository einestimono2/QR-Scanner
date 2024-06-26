package com.example.tranning_qr_scanner.data.model.barcode

import android.widget.Toast
import androidx.camera.core.impl.utils.ContextUtil.getApplicationContext
import com.example.tranning_qr_scanner.R
import com.example.tranning_qr_scanner.core.utils.BarcodeFormat
import com.example.tranning_qr_scanner.core.utils.BarcodeType
import com.example.tranning_qr_scanner.core.utils.Constants.SCAN_RESULT_ACTION_MAX_SPAN_COUNT
import com.example.tranning_qr_scanner.core.utils.Utilities.Companion.copyToClipboard
import com.example.tranning_qr_scanner.core.utils.Utilities.Companion.openUrl
import com.example.tranning_qr_scanner.core.utils.Utilities.Companion.share
import com.example.tranning_qr_scanner.presentation.scan_result.ScanResultActionModel
import timber.log.Timber

data class UrlModel(
    override val format: BarcodeFormat,
    override val type: BarcodeType,
    override val timeStamp: String?,
    override val rawValue: String,
    val title: String?,
    val url: String?
) : BarcodeModel() {
    override val actions = listOf(
        ScanResultActionModel(R.drawable.ic_open_web, R.string.open_url) {
            url?.let { url -> openUrl(it, url) }
        },
        ScanResultActionModel(R.drawable.ic_copy, R.string.copy) {
            url?.let { url -> copyToClipboard(it, url) }
        },
        ScanResultActionModel(R.drawable.ic_share, R.string.share) {
            Toast.makeText(it, "Feature 'Share' is unavailable!", Toast.LENGTH_SHORT).show()
        },
    )

    override val spanCount: Int = actions.size.coerceAtMost(SCAN_RESULT_ACTION_MAX_SPAN_COUNT)
}
