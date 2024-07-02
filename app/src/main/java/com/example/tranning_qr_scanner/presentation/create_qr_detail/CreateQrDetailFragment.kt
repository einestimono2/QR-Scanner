package com.example.tranning_qr_scanner.presentation.create_qr_detail

import android.view.LayoutInflater
import android.view.View.GONE
import androidx.navigation.fragment.navArgs
import com.example.tranning_qr_scanner.R
import com.example.tranning_qr_scanner.core.utils.extension.shortToast
import com.example.tranning_qr_scanner.databinding.CreateQrDetailFragmentBinding
import com.example.tranning_qr_scanner.presentation.base.BaseFragment
import com.google.android.material.tabs.TabLayout
import kotlin.properties.Delegates

class CreateQrDetailFragment : BaseFragment<CreateQrDetailFragmentBinding, Nothing>() {
    override fun inflateLayout() = CreateQrDetailFragmentBinding.inflate(layoutInflater)

    private val args: CreateQrDetailFragmentArgs by navArgs()
    private var type by Delegates.notNull<Int>()


    override fun bind() {
        type = args.id

        binding.createQRFragAppBarTitle.setNavigationOnClickListener {
            pop()
        }

        //! TODO: HARDCODED STRING
        initContent()
    }

    private fun initContent() {
        when (type) {
            /* Personal */
            R.id.createQRFrag_personal_emailAddress -> initPersonalContent("Create QR Email")
            R.id.createQRFrag_personal_website -> initPersonalContent("Create QR Website")
            R.id.createQRFrag_personal_contactInfo -> initPersonalContent("Create QR Contact")
            R.id.createQRFrag_personal_telephone -> initPersonalContent("Create QR Telephone")
            R.id.createQRFrag_personal_message -> initPersonalContent("Create QR Message")

            /* Card */
            R.id.createQRFrag_businessCard -> initBusinessCardContent()

            /* Social */
            R.id.createQRFrag_social_whatsapp -> initSocialContent("Create QR Whatsapp")
            R.id.createQRFrag_social_facebook -> initSocialContent("Create QR Facebook")
            R.id.createQRFrag_social_twitter -> initSocialContent("Create QR Twitter")
            R.id.createQRFrag_social_instagram -> initSocialContent("Create QR Instagram")
            R.id.createQRFrag_social_tiktok -> initSocialContent("Create QR Tiktok")
            R.id.createQRFrag_social_telegram -> initSocialContent("Create QR Telegram")
            R.id.createQRFrag_social_youtube -> initSocialContent("Create QR Youtube")
            R.id.createQRFrag_social_spotify -> initSocialContent("Create QR Spotify")

            /* Utilities */
            R.id.createQRFrag_utilities_wifi -> initUtilitiesContent("Create QR Wifi")
            R.id.createQRFrag_utilities_text -> initUtilitiesContent("Create QR Text")
            R.id.createQRFrag_utilities_calendar -> initUtilitiesContent("Create QR Calendar")

            /* Other */
            else -> {
                requireContext().shortToast("Something went wrong!")
            }
        }
    }

    private fun initPersonalContent(title: String) {
        binding.createQRFragAppBarTitle.title = title

        binding.createQRFragTabs.apply {
            addTab(this.newTab().setText("Email"))
            addTab(this.newTab().setText("Website"))
            addTab(this.newTab().setText("Contact"))
            addTab(this.newTab().setText("Telephone"))
            addTab(this.newTab().setText("Message"))

            addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    binding.createQrDetailFragmentContent.setDisplayedChild(tab!!.position)
                    binding.createQRFragAppBarTitle.title = "Create QR ${tab.text}"
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {}
                override fun onTabReselected(tab: TabLayout.Tab?) {}
            })
        }

        LayoutInflater.from(context).let {
            binding.createQrDetailFragmentContent.apply {
                addView(it.inflate(R.layout.create_qr_detail__form_email, this, false))
                addView(it.inflate(R.layout.create_qr_detail__form_website, this, false))
                addView(it.inflate(R.layout.create_qr_detail__form_contact, this, false))
                addView(it.inflate(R.layout.create_qr_detail__form_telephone, this, false))
                addView(it.inflate(R.layout.create_qr_detail__form_message, this, false))
            }
        }
    }

    private fun initBusinessCardContent() {
        binding.createQRFragAppBarTitle.title = "Create My Card"

        binding.createQRFragTabs.visibility = GONE

        binding.createQrDetailFragmentContent.addView(
            LayoutInflater.from(context).inflate(
                R.layout.create_qr_detail__form_business_card,
                binding.createQrDetailFragmentContent,
                false
            )
        )
    }

    private fun initSocialContent(title: String) {
        binding.createQRFragAppBarTitle.title = title

        binding.createQRFragTabs.apply {
            addTab(this.newTab().setText("WhatsApp"))
            addTab(this.newTab().setText("Facebook"))
            addTab(this.newTab().setText("Twitter"))
            addTab(this.newTab().setText("Instagram"))
            addTab(this.newTab().setText("Tiktok"))
            addTab(this.newTab().setText("Telegram"))
            addTab(this.newTab().setText("Youtube"))
            addTab(this.newTab().setText("Spotify"))

            addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    binding.createQrDetailFragmentContent.setDisplayedChild(tab!!.position)
                    binding.createQRFragAppBarTitle.title = "Create QR ${tab.text}"
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {}
                override fun onTabReselected(tab: TabLayout.Tab?) {}
            })
        }

        LayoutInflater.from(context).let {
            binding.createQrDetailFragmentContent.apply {
                addView(it.inflate(R.layout.create_qr_detail__form_whatsapp, this, false))
                addView(it.inflate(R.layout.create_qr_detail__form_social_general, this, false))
            }
        }
    }

    private fun initUtilitiesContent(title: String) {
        binding.createQRFragAppBarTitle.title = title

        binding.createQRFragTabs.apply {
            addTab(this.newTab().setText("Wifi"))
            addTab(this.newTab().setText("Text"))
            addTab(this.newTab().setText("Calendar"))

            addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    binding.createQrDetailFragmentContent.setDisplayedChild(tab!!.position)
                    binding.createQRFragAppBarTitle.title = "Create QR ${tab.text}"
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {}
                override fun onTabReselected(tab: TabLayout.Tab?) {}
            })
        }

        LayoutInflater.from(context).let {
            binding.createQrDetailFragmentContent.apply {
                addView(it.inflate(R.layout.create_qr_detail__form_wifi, this, false))
                addView(it.inflate(R.layout.create_qr_detail__form_text, this, false))
                addView(it.inflate(R.layout.create_qr_detail__form_calendar, this, false))
            }
        }
    }
}