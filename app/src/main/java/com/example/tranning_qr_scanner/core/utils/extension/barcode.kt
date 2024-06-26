package com.example.tranning_qr_scanner.core.utils.extension

import com.example.tranning_qr_scanner.core.utils.BarcodeFormat
import com.example.tranning_qr_scanner.core.utils.BarcodeType
import com.example.tranning_qr_scanner.core.utils.ContactType
import com.example.tranning_qr_scanner.core.utils.WifiSecurity
import com.google.mlkit.vision.barcode.common.Barcode

val Int.toBarcodeType: BarcodeType
    get() = when (this) {
        Barcode.TYPE_CONTACT_INFO -> BarcodeType.CONTACT
        Barcode.TYPE_EMAIL -> BarcodeType.EMAIL
        Barcode.TYPE_PHONE -> BarcodeType.PHONE
        Barcode.TYPE_PRODUCT -> BarcodeType.PRODUCT
        Barcode.TYPE_SMS -> BarcodeType.SMS
        Barcode.TYPE_TEXT -> BarcodeType.TEXT
        Barcode.TYPE_URL -> BarcodeType.URL
        Barcode.TYPE_WIFI -> BarcodeType.WIFI
        Barcode.TYPE_GEO -> BarcodeType.GEO
        Barcode.TYPE_CALENDAR_EVENT -> BarcodeType.CALENDAR
        Barcode.TYPE_DRIVER_LICENSE -> BarcodeType.DRIVER_LICENSE
        Barcode.TYPE_ISBN -> BarcodeType.ISBN
        else -> BarcodeType.UNKNOWN
    }

val Int.toBarcodeFormat: BarcodeFormat
    get() = when (this) {
        Barcode.FORMAT_AZTEC -> BarcodeFormat.AZTEC
        Barcode.FORMAT_CODABAR -> BarcodeFormat.CODABAR
        Barcode.FORMAT_CODE_39 -> BarcodeFormat.CODE_39
        Barcode.FORMAT_CODE_93 -> BarcodeFormat.CODE_93
        Barcode.FORMAT_CODE_128 -> BarcodeFormat.CODE_128
        Barcode.FORMAT_DATA_MATRIX -> BarcodeFormat.DATA_MATRIX
        Barcode.FORMAT_EAN_8 -> BarcodeFormat.EAN_8
        Barcode.FORMAT_EAN_13 -> BarcodeFormat.EAN_13
        Barcode.FORMAT_ITF -> BarcodeFormat.ITF
        Barcode.FORMAT_PDF417 -> BarcodeFormat.PDF417
        Barcode.FORMAT_QR_CODE -> BarcodeFormat.QR_CODE
        Barcode.FORMAT_UPC_A -> BarcodeFormat.UPC_A
        Barcode.FORMAT_UPC_E -> BarcodeFormat.UPC_E
        else -> BarcodeFormat.UNKNOWN
    }

val Int?.toContactType: ContactType
    get() = when (this) {
        Barcode.Email.TYPE_HOME -> ContactType.HOME
        Barcode.Email.TYPE_WORK -> ContactType.WORK
        else -> ContactType.OTHER
    }

val Int?.toWifiSecurity: WifiSecurity
    get() = when (this) {
        Barcode.WiFi.TYPE_WEP -> WifiSecurity.WEP
        Barcode.WiFi.TYPE_WPA -> WifiSecurity.WPA
        else -> WifiSecurity.OPEN
    }
