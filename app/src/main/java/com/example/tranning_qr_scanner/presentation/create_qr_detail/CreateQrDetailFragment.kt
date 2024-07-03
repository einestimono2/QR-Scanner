package com.example.tranning_qr_scanner.presentation.create_qr_detail

import android.view.View.GONE
import androidx.navigation.fragment.navArgs
import com.example.tranning_qr_scanner.R
import com.example.tranning_qr_scanner.core.utils.extension.shortToast
import com.example.tranning_qr_scanner.databinding.CreateQrDetailFragmentBinding
import com.example.tranning_qr_scanner.presentation.base.BaseFragment
import com.example.tranning_qr_scanner.presentation.create_qr_detail.contents.*
import com.example.tranning_qr_scanner.presentation.language.LanguageViewModel
import com.google.android.material.tabs.TabLayout
import timber.log.Timber
import kotlin.properties.Delegates

class CreateQrDetailFragment : BaseFragment<CreateQrDetailFragmentBinding, CreateQrDetailViewModel>() {
    override fun inflateLayout() = CreateQrDetailFragmentBinding.inflate(layoutInflater)
    override val viewModelClass = CreateQrDetailViewModel::class.java

    private val args: CreateQrDetailFragmentArgs by navArgs()
    private var type by Delegates.notNull<Int>()

    override fun bind() {
        type = args.id

        binding.createQRFragAppBarTitle.setNavigationOnClickListener { pop() }
        viewModel.canCreate.observe(this) {
            Timber.v(it.toString())
            binding.createQrDetailFragmentBtn.isEnabled = it
        }

        //! TODO: HARDCODED STRING
        initContent()

        binding.createQrDetailFragmentBtn.setOnClickListener {
            handleClick()
        }
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

        binding.createQrDetailFragmentContent.apply {
            addView(CreateQrEmail(context))
            addView(CreateQrWebsite(context))
            addView(CreateQrContact(context))
            addView(CreateQrTelephone(context))
            addView(CreateQrMessage(context))
        }

    }

    private fun initBusinessCardContent() {
        binding.createQRFragAppBarTitle.title = "Create My Card"
        binding.createQRFragTabs.visibility = GONE

        binding.createQrDetailFragmentContent.addView(
            CreateQrBusinessCard(requireContext())
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

        binding.createQrDetailFragmentContent.apply {
            addView(CreateQrWhatsapp(context))
            addView(CreateQrSocial(context))
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

        binding.createQrDetailFragmentContent.apply {
            addView(CreateQrWifi(context))
            addView(CreateQrText(context))
            addView(CreateQrCalendar(context))
        }
    }

    private fun handleClick() {
        (binding.createQrDetailFragmentContent.currentView as CreateQrEmail).handleCreateQr()
    }
}