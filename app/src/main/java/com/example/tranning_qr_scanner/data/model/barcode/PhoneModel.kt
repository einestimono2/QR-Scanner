package com.example.tranning_qr_scanner.data.model.barcode

import android.widget.Toast
import com.example.tranning_qr_scanner.R
import com.example.tranning_qr_scanner.core.utils.BarcodeFormat
import com.example.tranning_qr_scanner.core.utils.BarcodeType
import com.example.tranning_qr_scanner.core.utils.Constants.SCAN_RESULT_ACTION_MAX_SPAN_COUNT
import com.example.tranning_qr_scanner.core.utils.ContactType
import com.example.tranning_qr_scanner.presentation.scan_result.ScanResultActionModel
import timber.log.Timber

data class PhoneModel(
    override val format: BarcodeFormat,
    override val type: BarcodeType,
    override val timeStamp: String?,
    override val rawValue: String,
    val name: String?,
    val number: String?,
    val contactType: ContactType?
) : BarcodeModel(){
    override val actions = listOf(
        ScanResultActionModel(R.drawable.ic_add_contact, R.string.add_contact) {
            Toast.makeText(it, "Feature 'Add contact' is unavailable!", Toast.LENGTH_SHORT).show()
        },
        ScanResultActionModel(R.drawable.ic_call_phone, R.string.call) {
            Toast.makeText(it, "Call 'Share' is unavailable!", Toast.LENGTH_SHORT).show()
        },
        ScanResultActionModel(R.drawable.ic_copy, R.string.copy) {
            Toast.makeText(it, "Feature 'Copy' is unavailable!", Toast.LENGTH_SHORT).show()
        }
    )

    override val spanCount: Int = actions.size.coerceAtMost(SCAN_RESULT_ACTION_MAX_SPAN_COUNT)
}
