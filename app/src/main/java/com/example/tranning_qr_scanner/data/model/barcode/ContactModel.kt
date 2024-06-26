package com.example.tranning_qr_scanner.data.model.barcode

import android.widget.Toast
import com.example.tranning_qr_scanner.R
import com.example.tranning_qr_scanner.core.utils.BarcodeFormat
import com.example.tranning_qr_scanner.core.utils.BarcodeType
import com.example.tranning_qr_scanner.core.utils.Constants.SCAN_RESULT_ACTION_MAX_SPAN_COUNT
import com.example.tranning_qr_scanner.core.utils.ContactType
import com.example.tranning_qr_scanner.presentation.scan_result.ScanResultActionModel
import timber.log.Timber

data class ContactModel(
    override val format: BarcodeFormat,
    override val type: BarcodeType,
    override val timeStamp: String?,
    override val rawValue: String,
    val name: String?,
    val phone: String?,
    val phoneType: ContactType?,
    val email: String?,
    val emailType: ContactType?,
    val address: String?,
    val url: String?,
) : BarcodeModel() {
    override val actions = listOf(
        ScanResultActionModel(R.drawable.ic_add_contact, R.string.add_contact) {
            Toast.makeText(it, "Feature 'Add contact' is unavailable!", Toast.LENGTH_SHORT).show()
        },
        ScanResultActionModel(R.drawable.ic_call_phone, R.string.call) {
            Toast.makeText(it, "Feature 'Call' is unavailable!", Toast.LENGTH_SHORT).show()
        },
        ScanResultActionModel(R.drawable.ic_show_on_map, R.string.direction) {
            Toast.makeText(it, "Feature 'Direction' is unavailable!", Toast.LENGTH_SHORT).show()
        },
        ScanResultActionModel(R.drawable.ic_copy, R.string.copy) {
            Toast.makeText(it, "Feature 'Copy' is unavailable!", Toast.LENGTH_SHORT).show()
        },
        ScanResultActionModel(R.drawable.ic_send_email, R.string.send_mail) {
            Toast.makeText(it, "Feature 'Send email' is unavailable!", Toast.LENGTH_SHORT).show()
        }
    )

    override val spanCount: Int = actions.size.coerceAtMost(SCAN_RESULT_ACTION_MAX_SPAN_COUNT)
}
