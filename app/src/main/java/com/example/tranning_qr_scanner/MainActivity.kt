package com.example.tranning_qr_scanner

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.tranning_qr_scanner.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

//    override fun onRequestPermissionsResult(
//        requestCode: Int,
//        permissions: Array<String>,
//        grantResults: IntArray
//    ) {
//        if (requestCode == PERMISSION_CAMERA_REQUEST) {
//            if (isCameraPermissionGranted()) {
//                // start camera
//            } else {
//                Log.e("MainActivity", "no camera permission")
//            }
//        }
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//    }
//
//    private fun isCameraPermissionGranted(): Boolean {
//        return ContextCompat.checkSelfPermission(
//            baseContext,
//            android.Manifest.permission.CAMERA
//        ) == PackageManager.PERMISSION_GRANTED
//    }
//
//    companion object {
//        private const val PERMISSION_CAMERA_REQUEST = 1
//    }
}

//private fun checkCameraPermission() {
//    try {
//        val requiredPermissions = arrayOf(Manifest.permission.CAMERA)
//        ActivityCompat.requestPermissions(this, requiredPermissions, 0)
//    } catch (e: IllegalArgumentException) {
//        checkIfCameraPermissionIsGranted()
//    }
//}

//private fun checkIfCameraPermissionIsGranted() {
//    if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
//        // Permission granted: start the preview
//        startCamera()
//    } else {
//        // Permission denied
//        MaterialAlertDialogBuilder(this)
//            .setTitle("Permission required")
//            .setMessage("This application needs to access the camera to process barcodes")
//            .setPositiveButton("Ok") { _, _ ->
//                // Keep asking for permission until granted
//                checkCameraPermission()
//            }
//            .setCancelable(false)
//            .create()
//            .apply {
//                setCanceledOnTouchOutside(false)
//                show()
//            }
//    }
//}

//override fun onRequestPermissionsResult(
//    requestCode: Int,
//    permissions: Array<out String>,
//    grantResults: IntArray
//) {
//    super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//    checkIfCameraPermissionIsGranted()
//}