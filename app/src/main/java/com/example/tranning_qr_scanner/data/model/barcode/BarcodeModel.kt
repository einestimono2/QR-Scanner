package com.example.tranning_qr_scanner.data.model.barcode

import android.os.Parcelable
import androidx.annotation.Keep
import com.example.tranning_qr_scanner.core.utils.BarcodeFormat
import com.example.tranning_qr_scanner.core.utils.BarcodeType
import com.example.tranning_qr_scanner.presentation.scan_result.ScanResultActionModel
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
open class BarcodeModel(
    open val format: BarcodeFormat = BarcodeFormat.UNKNOWN,
    open val type: BarcodeType = BarcodeType.UNKNOWN,
    open val timeStamp: String? = null,
    open val rawValue: String = "",
) : Parcelable {
    @IgnoredOnParcel
    open val actions: List<ScanResultActionModel> = emptyList()
    @IgnoredOnParcel
    open val spanCount: Int = 0
}
