package com.example.tranning_qr_scanner.core.ui

import android.content.Context
import android.text.Spannable
import android.text.SpannableString
import android.text.style.TextAppearanceSpan
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.GridLayoutManager
import com.example.tranning_qr_scanner.R
import com.example.tranning_qr_scanner.core.utils.BarcodeType
import com.example.tranning_qr_scanner.data.model.barcode.*
import com.example.tranning_qr_scanner.databinding.ScanResultContentBinding
import com.example.tranning_qr_scanner.presentation.scan_result.ScanResultActionAdapter

class ScanResultContent : ConstraintLayout {
    private lateinit var binding: ScanResultContentBinding

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    fun initContent(barcode: BarcodeModel) {
        binding = ScanResultContentBinding.inflate(LayoutInflater.from(this.context), this, true)

        when (barcode.type) {
            BarcodeType.URL -> bindUrlContent(barcode as UrlModel)
            BarcodeType.EMAIL -> bindEmailContent(barcode as EmailModel)
            BarcodeType.SMS -> bindSmsContent(barcode as SmsModel)
            BarcodeType.TEXT -> bindTextContent(barcode as TextModel)
            BarcodeType.WIFI -> bindWifiContent(barcode as WifiModel)
            BarcodeType.CALENDAR -> bindCalendarContent(barcode as CalendarModel)
            BarcodeType.CONTACT -> bindContactContent(barcode as ContactModel)
            BarcodeType.GEO -> bindGeoContent(barcode as GeoModel)
            BarcodeType.PHONE -> bindPhoneContent(barcode as PhoneModel)
            else -> {}
        }

        binding.scanResultContentActions.layoutManager = GridLayoutManager(context, barcode.spanCount)
        binding.scanResultContentActions.adapter = ScanResultActionAdapter(barcode.actions)
        binding.scanResultContentActions.suppressLayout(true) // Never scroll
    }

    private fun bindUrlContent(barcode: UrlModel) {
        binding.scanResultContentIcon.setImageResource(R.drawable.ic_scan_result_website)
        binding.scanResultContentType.text = resources.getString(R.string.website)
        binding.scanResultContentDescription.apply {
            visibility = VISIBLE
            text = barcode.url
        }

        (binding.scanResultContentActions.layoutParams as LayoutParams).apply {
            this.topToBottom = binding.scanResultContentTypeContainer.id
        }
    }

    private fun bindEmailContent(barcode: EmailModel) {
        binding.scanResultContentIcon.setImageResource(R.drawable.ic_scan_result_email)
        binding.scanResultContentType.text = resources.getString(R.string.email)
        binding.scanResultContentDescription.visibility = GONE

        binding.scanResultContentContentContainer.addView(
            makeSpanTextView("${resources.getString(R.string.email_to)}: ${barcode.email}")
        )
        binding.scanResultContentContentContainer.addView(
            makeSpanTextView("${resources.getString(R.string.email_subject)}: ${barcode.subject}")
        )
        binding.scanResultContentContentContainer.addView(
            makeSpanTextView("${resources.getString(R.string.email_content)}: ${barcode.message}")
        )
    }

    private fun bindSmsContent(barcode: SmsModel) {
        binding.scanResultContentIcon.setImageResource(R.drawable.ic_scan_result_message)
        binding.scanResultContentType.text = resources.getString(R.string.message)
        binding.scanResultContentDescription.visibility = GONE

        binding.scanResultContentContentContainer.addView(
            makeSpanTextView("${resources.getString(R.string.message_tel)}: ${barcode.number}")
        )
        binding.scanResultContentContentContainer.addView(
            makeSpanTextView("${resources.getString(R.string.message_content)}: ${barcode.message}")
        )
    }

    private fun bindTextContent(barcode: TextModel) {
        binding.scanResultContentIcon.setImageResource(R.drawable.ic_scan_result_text)
        binding.scanResultContentType.text = resources.getString(R.string.text)
        binding.scanResultContentDescription.visibility = GONE

        binding.scanResultContentContentContainer.addView(
            makeSpanTextView("${resources.getString(R.string.text_content)}: ${barcode.text}")
        )
    }

    private fun bindWifiContent(barcode: WifiModel) {
        binding.scanResultContentIcon.setImageResource(R.drawable.ic_scan_result_wifi)
        binding.scanResultContentType.text = resources.getString(R.string.wifi)
        binding.scanResultContentDescription.visibility = GONE

        binding.scanResultContentContentContainer.addView(
            makeSpanTextView("${resources.getString(R.string.wifi_name)}: ${barcode.ssid}")
        )
        binding.scanResultContentContentContainer.addView(
            makeSpanTextView("${resources.getString(R.string.wifi_security)}: ${barcode.security}")
        )
        binding.scanResultContentContentContainer.addView(
            makeSpanTextView("${resources.getString(R.string.wifi_password)}: ${barcode.password}")
        )
    }

    private fun bindCalendarContent(barcode: CalendarModel) {
        binding.scanResultContentIcon.setImageResource(R.drawable.ic_scan_result_calendar)
        binding.scanResultContentType.text = resources.getString(R.string.calendar)
        binding.scanResultContentDescription.visibility = GONE

        binding.scanResultContentContentContainer.addView(
            makeSpanTextView("${resources.getString(R.string.calendar_subject)}: ${barcode.organizer}")
        )
        binding.scanResultContentContentContainer.addView(
            makeSpanTextView("${resources.getString(R.string.calendar_start)}: ${barcode.start}")
        )
        binding.scanResultContentContentContainer.addView(
            makeSpanTextView("${resources.getString(R.string.calendar_end)}: ${barcode.end}")
        )
        binding.scanResultContentContentContainer.addView(
            makeSpanTextView("${resources.getString(R.string.calendar_note)}: ${barcode.description}")
        )
        binding.scanResultContentContentContainer.addView(
            makeSpanTextView("${resources.getString(R.string.calendar_address)}: ${barcode.address}")
        )
    }

    private fun bindContactContent(barcode: ContactModel) {
        binding.scanResultContentIcon.setImageResource(R.drawable.ic_scan_result_contact)
        binding.scanResultContentType.text = resources.getString(R.string.contacts)
        binding.scanResultContentDescription.visibility = GONE

        binding.scanResultContentContentContainer.addView(
            makeSpanTextView("${resources.getString(R.string.contacts_name)}: ${barcode.name}")
        )
        binding.scanResultContentContentContainer.addView(
            makeSpanTextView("${resources.getString(R.string.contacts_tel)}: ${barcode.phone}")
        )
        binding.scanResultContentContentContainer.addView(
            makeSpanTextView("${resources.getString(R.string.contacts_email)}: ${barcode.email}")
        )
        binding.scanResultContentContentContainer.addView(
            makeSpanTextView("${resources.getString(R.string.contacts_address)}: ${barcode.address}")
        )
        binding.scanResultContentContentContainer.addView(
            makeSpanTextView("${resources.getString(R.string.contacts_url)}: ${barcode.address}")
        )
    }

    private fun bindGeoContent(barcode: GeoModel) {
        binding.scanResultContentIcon.setImageResource(R.drawable.ic_scan_result_map)
        binding.scanResultContentType.text = resources.getString(R.string.map)
        binding.scanResultContentDescription.visibility = GONE

        binding.scanResultContentContentContainer.addView(
            makeSpanTextView("${resources.getString(R.string.map_long)}: ${barcode.longitude}")
        )
        binding.scanResultContentContentContainer.addView(
            makeSpanTextView("${resources.getString(R.string.map_lat)}: ${barcode.latitude}")
        )
    }

    private fun bindPhoneContent(barcode: PhoneModel) {
        binding.scanResultContentIcon.setImageResource(R.drawable.ic_scan_result_telephone)
        binding.scanResultContentType.text = resources.getString(R.string.telephone)
        binding.scanResultContentDescription.visibility = GONE

        binding.scanResultContentContentContainer.addView(
            makeSpanTextView("${resources.getString(R.string.telephone_name)}: ${barcode.name}")
        )
        binding.scanResultContentContentContainer.addView(
            makeSpanTextView("${resources.getString(R.string.telephone_tel)}: ${barcode.number}")
        )
    }

    private fun makeSpanTextView(str: String): TextView {
        val spanLen = str.split(":")[0].length

        return TextView(context).apply {
            layoutParams = LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)

            text = SpannableString(str).apply {
                setSpan(
                    TextAppearanceSpan(context, R.style.ScanResultContentSpan),
                    0,
                    spanLen + 1,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )

                setSpan(
                    TextAppearanceSpan(context, R.style.ScanResultContentText),
                    spanLen + 2,
                    str.length,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
            }
        }
    }
}