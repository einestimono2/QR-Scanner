package com.example.tranning_qr_scanner.core.utils

import android.os.Build
import com.example.tranning_qr_scanner.core.utils.extension.toBarcodeFormat
import com.example.tranning_qr_scanner.core.utils.extension.toBarcodeType
import com.example.tranning_qr_scanner.core.utils.extension.toContactType
import com.example.tranning_qr_scanner.core.utils.extension.toWifiSecurity
import com.example.tranning_qr_scanner.data.model.barcode.*
import com.google.mlkit.vision.barcode.common.Barcode
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

object Mappers {
    fun barcodeToBarcodeModel(barcode: Barcode): BarcodeModel {
        return if (barcode.format == Barcode.FORMAT_QR_CODE) {
            mapQrCode(barcode)
        } else {
            BarcodeModel(
                format = barcode.format.toBarcodeFormat,
                type = barcode.valueType.toBarcodeType,
                timeStamp = getDateTime(),
                rawValue = "${barcode.rawValue}"
            )
        }
    }

    private fun mapQrCode(barcode: Barcode): BarcodeModel {
        val format = barcode.format.toBarcodeFormat
        val type = barcode.valueType.toBarcodeType
        val rawValue = "${barcode.rawValue}"
        val dateTime = getDateTime()

        return when (barcode.valueType) {
            Barcode.TYPE_TEXT -> TextModel(format, type, dateTime, rawValue, barcode.displayValue)

            Barcode.TYPE_URL -> UrlModel(format, type, dateTime, rawValue, barcode.url!!.title, barcode.url!!.url)

            Barcode.TYPE_EMAIL -> EmailModel(
                format,
                type,
                dateTime,
                rawValue,
                barcode.email?.address,
                barcode.email?.subject,
                barcode.email?.body,
                barcode.email?.type.toContactType
            )

            Barcode.TYPE_WIFI -> WifiModel(
                format,
                type,
                dateTime,
                rawValue,
                barcode.wifi?.ssid,
                barcode.wifi?.password,
                barcode.wifi?.encryptionType.toWifiSecurity
            )

            Barcode.TYPE_CONTACT_INFO -> ContactModel(
                format,
                type,
                dateTime,
                rawValue,
                barcode.contactInfo?.name?.formattedName,
                barcode.contactInfo?.phones?.get(0)?.number,
                null,
                barcode.contactInfo?.emails?.get(0)?.address,
                null,
                barcode.contactInfo?.addresses?.get(0)?.addressLines?.joinToString(),
                barcode.contactInfo?.urls?.get(0)
            )

            Barcode.TYPE_PHONE -> PhoneModel(
                format, type, dateTime, rawValue, null, barcode.phone?.number, barcode.phone?.type.toContactType
            )

            Barcode.TYPE_SMS -> SmsModel(
                format, type, dateTime, rawValue, barcode.sms?.phoneNumber, barcode.sms?.message
            )

            Barcode.TYPE_CALENDAR_EVENT -> CalendarModel(
                format,
                type,
                dateTime,
                rawValue,
                barcode.calendarEvent!!.description,
                barcode.calendarEvent!!.start.toString(),
                barcode.calendarEvent!!.end.toString(),
                barcode.calendarEvent!!.organizer,
                barcode.calendarEvent!!.location,
            )

            Barcode.TYPE_GEO -> GeoModel(
                format, type, dateTime, rawValue, barcode.geoPoint?.lat, barcode.geoPoint?.lng
            )

//            Barcode.TYPE_ISBN -> TextModel(format, type, dateTime, rawValue, isbn = rawValue)

            else -> return BarcodeModel(format, type, dateTime, rawValue)
        }
    }

    private fun getDateTime(): String {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val current = LocalDateTime.now()
            val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")
            current.format(formatter)
        } else {
            val date = Date()
            val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())
            formatter.format(date)
        }
    }
}