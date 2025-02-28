package com.example.tranning_qr_scanner.core.utils.helper

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.tranning_qr_scanner.core.utils.DialogUtils
import com.example.tranning_qr_scanner.core.utils.Utilities

class PermissionHelper {
    companion object {
        /**/
        fun isCameraPermissionGranted(context: Context): Boolean = ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.CAMERA
        ) == PackageManager.PERMISSION_GRANTED

        /**/
        fun requestCameraPermission(context: Context, fragment: Fragment, onDeny: () -> Unit) {
            val requestPermissionLauncher: ActivityResultLauncher<String>

            if (!isCameraPermissionGranted(context)) {
                requestPermissionLauncher = fragment.registerForActivityResult(
                    ActivityResultContracts.RequestPermission()
                ) {
                    if (!it) {
                        DialogUtils.noPermissionDialog(
                            context,
                            onDeny = {
                                onDeny()
                            }, onAllow = {
                                Utilities.openAppSettings(context)
                            }
                        )
                    }
                }

                requestPermissionLauncher.launch(
                    Manifest.permission.CAMERA
                )
            }
        }
    }
}