package com.example.tranning_qr_scanner.features.onboarding.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.tranning_qr_scanner.R
import com.example.tranning_qr_scanner.databinding.LanguageCardBinding
import com.example.tranning_qr_scanner.features.onboarding.model.LanguageModel
import com.example.tranning_qr_scanner.features.onboarding.viewmodel.LanguageViewModel

class LanguageAdapter(
    private val languages: List<LanguageModel>,
    private val context: Context,
    private val viewModel: LanguageViewModel
) : RecyclerView.Adapter<LanguageAdapter.LanguageViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LanguageViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemBinding = LanguageCardBinding.inflate(inflater, parent, false)

        return LanguageViewHolder(itemBinding)
    }

    override fun getItemCount(): Int {
        return languages.size;
    }

    override fun onBindViewHolder(holder: LanguageViewHolder, position: Int) {
        holder.bind(languages[position])
    }

    /* Holder */
    inner class LanguageViewHolder(
        private val itemBinding: LanguageCardBinding
    ) : RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(language: LanguageModel) = itemBinding.apply {
            languageCardIcon.setImageResource(language.icon)
            languageCardLabel.text = language.name

            val whiteColor: Int = context.getColor(R.color.white)
            val primaryColor: Int = context.getColor(R.color.primary)
            val blackColor: Int = context.getColor(R.color.black)

            if (viewModel.selectedLanguage == language.code) {
                languageCardContainer.setBackgroundColor(primaryColor)
                languageCardLabel.setTextColor(whiteColor)
            } else {
                languageCardContainer.setBackgroundColor(whiteColor)
                languageCardLabel.setTextColor(blackColor)
            }

            languageCardContainer.setOnClickListener {
                if (viewModel.selectedLanguage != language.code) {
                    viewModel.onLanguageChange(language.code)
                    notifyDataSetChanged()
                }
            }
        }
    }
}
