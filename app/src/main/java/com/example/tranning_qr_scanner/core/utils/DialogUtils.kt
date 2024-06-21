package com.example.tranning_qr_scanner.core.utils

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.Window
import com.example.tranning_qr_scanner.R
import com.example.tranning_qr_scanner.databinding.NoPermissionDialogBinding
import timber.log.Timber

object DialogUtils {
    fun noPermissionDialog(context: Context, onDeny: () -> Unit, onAllow: () -> Unit) {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val binding = NoPermissionDialogBinding.inflate(inflater)

        val dialog: Dialog = Dialog(context).apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setCancelable(false)
            setContentView(binding.root)

            binding.noPermissionDialogBtnAllow.setOnClickListener{
                dismiss()
                onAllow()
            }

            binding.noPermissionDialogBtnDeny.setOnClickListener{
                dismiss()
                onDeny()
            }
        }

        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

        dialog.show()
    }
}