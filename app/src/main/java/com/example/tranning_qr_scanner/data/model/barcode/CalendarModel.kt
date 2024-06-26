package com.example.tranning_qr_scanner.data.model.barcode

import android.widget.Toast
import com.example.tranning_qr_scanner.R
import com.example.tranning_qr_scanner.core.utils.BarcodeFormat
import com.example.tranning_qr_scanner.core.utils.BarcodeType
import com.example.tranning_qr_scanner.core.utils.Constants.SCAN_RESULT_ACTION_MAX_SPAN_COUNT
import com.example.tranning_qr_scanner.presentation.scan_result.ScanResultActionModel
import timber.log.Timber

data class CalendarModel(
    override val format: BarcodeFormat,
    override val type: BarcodeType,
    override val timeStamp: String?,
    override val rawValue: String,
    val description: String?,
    val start: String?,
    val end: String?,
    val organizer: String?,
    val address: String?,
) : BarcodeModel() {
    override val actions = listOf(
        ScanResultActionModel(R.drawable.ic_add_event, R.string.add_event) {
            Toast.makeText(it, "Feature 'Add event' is unavailable!", Toast.LENGTH_SHORT).show()
        },
        ScanResultActionModel(R.drawable.ic_send_email, R.string.send_mail) {
            Toast.makeText(it, "Feature 'Send email' is unavailable!", Toast.LENGTH_SHORT).show()
        },
        ScanResultActionModel(R.drawable.ic_copy, R.string.copy) {
            Toast.makeText(it, "Feature 'Copy' is unavailable!", Toast.LENGTH_SHORT).show()
        },
    )

    override val spanCount: Int = actions.size.coerceAtMost(SCAN_RESULT_ACTION_MAX_SPAN_COUNT)
}
