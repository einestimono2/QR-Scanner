package com.example.tranning_qr_scanner.presentation.intro

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tranning_qr_scanner.databinding.IntroItemBinding
import com.example.tranning_qr_scanner.data.model.IntroModel

class IntroAdapter(
    private val listIntro: List<IntroModel>,
    private val context: Context
) : RecyclerView.Adapter<IntroAdapter.IntroViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IntroViewHolder {
        val itemBinding = IntroItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return IntroViewHolder(itemBinding)
    }

    override fun getItemCount(): Int {
        return listIntro.size
    }

    override fun onBindViewHolder(holder: IntroViewHolder, position: Int) {
        holder.bind(listIntro[position])
    }

    inner class IntroViewHolder(
        private val itemBinding: IntroItemBinding
    ) : RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(intro: IntroModel) = itemBinding.apply {
            introItemImg.setImageResource(intro.image)
            introItemTitle.text = context.getString(intro.title)
            introItemContent.text = context.getString(intro.content)
        }

    }
}
