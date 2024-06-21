package com.example.tranning_qr_scanner.presentation.home

import android.annotation.SuppressLint
import android.app.Dialog
import android.view.LayoutInflater
import androidx.fragment.app.FragmentManager
import com.example.tranning_qr_scanner.databinding.TutorialScanBsdFragmentBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class TutorialScanBSDFragment : BottomSheetDialogFragment() {
    private lateinit var binding: TutorialScanBsdFragmentBinding

    companion object {
        fun show(manage: FragmentManager) = TutorialScanBSDFragment().apply {
            isCancelable = false
        }.show(manage, "Tutorial Scan Bottom Sheet Dialog Fragment")
    }

    @SuppressLint("RestrictedApi")
    override fun setupDialog(dialog: Dialog, style: Int) {
        binding = TutorialScanBsdFragmentBinding.inflate(LayoutInflater.from(context))
        dialog.setContentView(binding.root)

        binding.tutorialScanBtnGotIt.setOnClickListener {
            dismiss()
        }

        // TODO: Content chưa giống figma ở phần chia đoạn
    }
}