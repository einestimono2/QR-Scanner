package com.example.tranning_qr_scanner.presentation.scan_qr

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import android.util.Size
import android.view.ScaleGestureDetector
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.OptIn
import androidx.camera.core.*
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import com.example.tranning_qr_scanner.R
import com.example.tranning_qr_scanner.core.utils.Constants
import com.example.tranning_qr_scanner.core.utils.Mappers
import com.example.tranning_qr_scanner.core.utils.Utilities
import com.example.tranning_qr_scanner.core.utils.extension.shortToast
import com.example.tranning_qr_scanner.core.utils.helper.AppCache
import com.example.tranning_qr_scanner.core.utils.helper.PermissionHelper
import com.example.tranning_qr_scanner.databinding.ScanQrFragmentBinding
import com.example.tranning_qr_scanner.presentation.base.BaseFragment
import com.example.tranning_qr_scanner.presentation.home.TutorialScanBSDFragment
import com.google.common.util.concurrent.ListenableFuture
import com.google.mlkit.vision.barcode.BarcodeScannerOptions
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.common.InputImage
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import java.math.RoundingMode
import java.text.DecimalFormat
import java.util.concurrent.ExecutionException
import javax.inject.Inject

@AndroidEntryPoint
class ScanQRFragment : BaseFragment<ScanQrFragmentBinding, Nothing>() {
    override fun inflateLayout() = ScanQrFragmentBinding.inflate(layoutInflater)

    private lateinit var mCamera: Camera
    private var mCameraProvider: ProcessCameraProvider? = null
    private lateinit var mCameraProviderFuture: ListenableFuture<ProcessCameraProvider>

    private lateinit var pickVisualMedia: ActivityResultLauncher<PickVisualMediaRequest>

    @Inject
    lateinit var appCache: AppCache

    override fun bind() {
        PermissionHelper.requestCameraPermission(requireContext(), this) {
            navigate(R.id.noPermissionFragment, true)
        }

        /**/
        mCameraProviderFuture = ProcessCameraProvider.getInstance(requireContext())

        /* Gallery */
        galleryControl()
    }

    override fun onResume() {
        super.onResume()

        binding.scanQRFragZoomLevel.text = getString(R.string.default_zoom_level)
        binding.scanQRFragBtnFlashlight.setBackgroundColor(
            ContextCompat.getColor(
                this.requireContext(),
                R.color.scanOption
            )
        )

        if (PermissionHelper.isCameraPermissionGranted(requireContext())) {
            if (appCache.readValue(Constants.FIRST_TIME_TUTORIAL_SCAN_QR_KEY) != null) {
                TutorialScanBSDFragment.show(parentFragmentManager) {
                    setupCamera()
                }
            } else {
                setupCamera()
            }
        }
    }

    private fun setupCamera() {
        mCameraProvider?.unbindAll()
        mCameraProviderFuture.addListener({
            try {
                mCameraProvider = mCameraProviderFuture.get()
                processScan()
            } catch (e: ExecutionException) {
                Timber.e("An error occurred: $e")

            } catch (e: InterruptedException) {
                Timber.e("An error occurred: $e")
            }
        }, ContextCompat.getMainExecutor(requireContext()))
    }

    @OptIn(ExperimentalGetImage::class)
    private fun processScan() {
        val preview = Preview.Builder().build()
        val imageAnalysis = ImageAnalysis.Builder()
//            .setTargetResolution(Size(720, 1280))
            .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST).build()

        imageAnalysis.setAnalyzer(ContextCompat.getMainExecutor(requireContext())) { imageProxy: ImageProxy ->
            val image = InputImage.fromMediaImage(imageProxy.image!!, imageProxy.imageInfo.rotationDegrees)
            val options = BarcodeScannerOptions.Builder().build()
            val scanner = BarcodeScanning.getClient(options)

            scanner.process(image).addOnSuccessListener { barcodes: List<Barcode> -> handleSuccess(barcodes) }
                .addOnFailureListener { e: Exception -> handleFailure(e) }
                .addOnCompleteListener(ContextCompat.getMainExecutor(requireContext())) {
                    imageProxy.close()
                }
        }

//        val useFrontCam = sharedPreferences.getBoolean("front_cam_preference", false)
//        val hasFrontCamera =
//            mCameraProvider?.hasCamera(CameraSelector.DEFAULT_FRONT_CAMERA) ?: false
//
//        val cameraSelector = if (useFrontCam && hasFrontCamera) {
//            CameraSelector.DEFAULT_FRONT_CAMERA
//        } else {
//            CameraSelector.DEFAULT_BACK_CAMERA
//        }

        preview.setSurfaceProvider(binding.scanQRFragCamPreview.surfaceProvider)
        mCamera = mCameraProvider?.bindToLifecycle(
            this, CameraSelector.DEFAULT_BACK_CAMERA, imageAnalysis, preview
        )!!

        zoomControl()
        flashControl()
    }

    private fun galleryControl() {
        pickVisualMedia = registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { result: Uri? ->
            if (result != null) {
                // TODO: Picked image
                Timber.v("Developing...")
            }
        }

        binding.scanQRFragBtnGallery.setOnClickListener {
            pickVisualMedia.launch(
                PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
            )
        }
    }

    private fun zoomControl() {
        val maxZoom = mCamera.cameraInfo.zoomState.value!!.maxZoomRatio.toDouble()

        val format = DecimalFormat("0.#")
        format.setRoundingMode(RoundingMode.DOWN)

        val listener = object : ScaleGestureDetector.SimpleOnScaleGestureListener() {
            @SuppressLint("SetTextI18n")
            override fun onScale(detector: ScaleGestureDetector): Boolean {
                val scale = mCamera.cameraInfo.zoomState.value!!.zoomRatio * detector.scaleFactor

                mCamera.cameraControl.setZoomRatio(scale)
                if (scale in 1.0..maxZoom) {
                    binding.scanQRFragZoomLevel.text = "${format.format(scale)}x"
                }

                return true
            }
        }
        val scaleGestureDetector = ScaleGestureDetector(requireContext(), listener)

        binding.scanQRFragCamPreview.setOnTouchListener { view, event ->
            scaleGestureDetector.onTouchEvent(event)
            view.performClick()
            return@setOnTouchListener true
        }
    }

    private fun flashControl() {
        binding.scanQRFragBtnFlashlight.setOnClickListener {
            if (mCamera.cameraInfo.hasFlashUnit()) {
                if (mCamera.cameraInfo.torchState.value == TorchState.OFF) {
                    mCamera.cameraControl.enableTorch(true)
                    binding.scanQRFragBtnFlashlight.setBackgroundColor(
                        ContextCompat.getColor(this.requireContext(), R.color.ads)
                    )
                } else {
                    mCamera.cameraControl.enableTorch(false)
                    binding.scanQRFragBtnFlashlight.setBackgroundColor(
                        ContextCompat.getColor(this.requireContext(), R.color.scanOption)
                    )
                }
            } else {
                requireContext().shortToast("No flashlight found for this camera")
            }
        }
    }

    private fun handleSuccess(barcodes: List<Barcode>) {
        if (barcodes.isNotEmpty()) {
            binding.scanQRFragLoading.visibility = VISIBLE

            mCameraProvider?.unbindAll()
            Utilities.vibrate(requireContext(), 100)

            sendBarcodeData(barcodes[0])

            binding.scanQRFragLoading.visibility = GONE
        }
    }

    private fun sendBarcodeData(barcodeData: Barcode) {
        val barcode = Mappers.barcodeToBarcodeModel(barcodeData)

        // TODO: Save scan

        /**/
        val action = ScanQRFragmentDirections.actionScanQRFragmentToScanResultFragment(barcode)
        navigate(action)
    }

    private fun handleFailure(e: Exception) {
        Toast.makeText(requireContext(), "Failed to scan!", Toast.LENGTH_SHORT).show()

        Timber.e(e.message)
    }

    override fun onPause() {
        super.onPause()
        mCameraProvider?.unbindAll()
    }
}