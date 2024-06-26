package com.example.tranning_qr_scanner.presentation.scan_result

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tranning_qr_scanner.databinding.ScanResultActionItemBinding

data class ScanResultActionModel(val icon: Int, val label: Int, val onClick: (context: Context) -> Unit)

class ScanResultActionAdapter(private val items: List<ScanResultActionModel>) :
    RecyclerView.Adapter<ScanResultActionAdapter.ScanResultActionViewHolder>() {
    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScanResultActionViewHolder {
        context = parent.context
        val itemBinding = ScanResultActionItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ScanResultActionViewHolder(itemBinding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ScanResultActionViewHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class ScanResultActionViewHolder(
        private val itemBinding: ScanResultActionItemBinding
    ) : RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(item: ScanResultActionModel) = itemBinding.apply {
            scanResultActionItemIcon.setImageResource(item.icon)
            scanResultActionItemLabel.text = context.getString(item.label)

            scanResultActionItemIcon.setOnClickListener {
                item.onClick(context)
            }
        }

    }
}