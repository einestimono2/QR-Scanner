package com.example.tranning_qr_scanner.core.utils

import android.app.Activity
import android.content.*
import android.net.Uri
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager
import android.provider.Settings
import android.view.Window
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.core.impl.utils.ContextUtil.getApplicationContext
import androidx.camera.core.impl.utils.ContextUtil.getBaseContext
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.content.ContextCompat.startActivity
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.example.tranning_qr_scanner.R
import timber.log.Timber

class Utilities {
    companion object {
        fun openAppSettings(context: Context?) {
            val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
            val uri = Uri.fromParts("package", context?.packageName, null)
            intent.data = uri
            context?.startActivity(intent)
        }

        fun vibrate(context: Context, timeInMillis: Long) {
            val vibrator = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                val vibratorManager =
                    context.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager
                vibratorManager.defaultVibrator
            } else {
                context.getSystemService(AppCompatActivity.VIBRATOR_SERVICE) as Vibrator
            }

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                vibrator.vibrate(VibrationEffect.createOneShot(timeInMillis, 125))
            } else {
                vibrator.vibrate(timeInMillis)
            }
        }

        fun setSystemBars(window: Window, activity: Activity, hideStatusBar: Boolean) {
            val windowInsetsController = WindowCompat.getInsetsController(window, window.decorView)
            // Configure the behavior of the hidden system bars
            windowInsetsController.systemBarsBehavior =
                WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            // Hide the status bar
            if (hideStatusBar) {
                windowInsetsController.hide(WindowInsetsCompat.Type.statusBars())
            }
        }

        fun openUrl(context: Context, url: String) {
            var _url = url

            if (!_url.startsWith("http://") && !_url.startsWith("https://")) {
                _url = "http://$_url"
            }

            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            context.startActivity(browserIntent)
        }

        fun copyToClipboard(context: Context, text: String) {
            val clipboard: ClipboardManager =
                context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager

            val clip = ClipData.newPlainText("Copied Text", text)
            clipboard.setPrimaryClip(clip)

            Toast.makeText(context, context.getString(R.string.copied), Toast.LENGTH_SHORT).show()
        }

        fun share(context: Context, content: String) {
            val sendIntent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, content)
                type = "text/plain"
            }

            try {
                context.startActivity(sendIntent)
            } catch (e: ActivityNotFoundException) {
                Timber.e(e.message)
            }
        }
    }
}