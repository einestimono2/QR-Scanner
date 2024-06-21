package com.example.tranning_qr_scanner.presentation.scan_qr

import android.annotation.SuppressLint
import android.net.Uri
import android.util.Size
import android.view.ScaleGestureDetector
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
import com.example.tranning_qr_scanner.core.utils.extension.shortToast
import com.example.tranning_qr_scanner.core.utils.helper.PermissionHelper
import com.example.tranning_qr_scanner.databinding.ScanQrFragmentBinding
import com.example.tranning_qr_scanner.presentation.base.BaseFragment
import com.google.common.util.concurrent.ListenableFuture
import com.google.mlkit.vision.barcode.BarcodeScannerOptions
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.common.InputImage
import timber.log.Timber
import java.math.RoundingMode
import java.text.DecimalFormat
import java.util.concurrent.ExecutionException
import com.example.tranning_qr_scanner.core.utils.DialogUtils
import com.example.tranning_qr_scanner.core.utils.helper.AppCache
import com.example.tranning_qr_scanner.presentation.home.TutorialScanBSDFragment
import dagger.hilt.android.AndroidEntryPoint
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
        requestPermission()

        /**/
        mCameraProviderFuture = ProcessCameraProvider.getInstance(requireContext())

        /* Gallery */
        galleryControl()
    }

    override fun onResume() {
        super.onResume()

        if (PermissionHelper.isCameraPermissionGranted(requireContext())) {
            if (appCache.readValue(Constants.FIRST_TIME_TUTORIAL_SCAN_QR_KEY) == null) {
                TutorialScanBSDFragment.show(parentFragmentManager)
            }

            setupCamera()
        }
    }

    private fun requestPermission() {
        PermissionHelper.requestCameraPermission(requireContext(), this) {
            DialogUtils.noPermissionDialog(
                requireContext(),
                onDeny = {
                    navigate(R.id.noPermissionFragment, true)
                },
                onAllow = {
                    // TODO: Request again
                    requestPermission()
                }
            )
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
        val imageAnalysis = ImageAnalysis.Builder().setTargetResolution(Size(1280, 720))
            .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST).build()

        imageAnalysis.setAnalyzer(ContextCompat.getMainExecutor(requireContext())) { imageProxy: ImageProxy ->
            val image = InputImage.fromMediaImage(imageProxy.image!!, imageProxy.imageInfo.rotationDegrees)
            val options = BarcodeScannerOptions.Builder().build()
            val scanner = BarcodeScanning.getClient(options)
            scanner.process(image)
                .addOnSuccessListener { barcodes: List<Barcode> -> handleSuccess(barcodes) }
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
            this,
            CameraSelector.DEFAULT_BACK_CAMERA,
            imageAnalysis,
            preview
        )!!

        zoomControl()
        flashControl()
    }

    private fun galleryControl() {
        pickVisualMedia =
            registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { result: Uri? ->
                if (result != null) {
                    // TODO: Picked image
                }
            }

        binding.scanQRFragBtnGallery.setOnClickListener {
            pickVisualMedia.launch(
                PickVisualMediaRequest(
                    ActivityResultContracts.PickVisualMedia.ImageOnly
                )
            )
        }
    }

    private fun zoomControl() {
        val maxZoom = mCamera.cameraInfo.zoomState.value!!.maxZoomRatio.toDouble()

        val format = DecimalFormat("0.#")
        format.setRoundingMode(RoundingMode.DOWN);

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
                        ContextCompat.getColor(
                            this.requireContext(),
                            com.example.tranning_qr_scanner.R.color.ads
                        )
                    )
                } else {
                    mCamera.cameraControl.enableTorch(false)
                    binding.scanQRFragBtnFlashlight.setBackgroundColor(
                        ContextCompat.getColor(
                            this.requireContext(),
                            com.example.tranning_qr_scanner.R.color.scanOption
                        )
                    )
                }
            } else {
                requireContext().shortToast("No flashlight found for this camera")
            }
        }
    }

    private fun handleSuccess(barcodes: List<Barcode>) {
        for (barcode in barcodes) {
            Toast.makeText(context, "Value: " + barcode.rawValue, Toast.LENGTH_SHORT).show()
        }
    }

    private fun handleFailure(e: Exception) {
        Toast.makeText(
            requireContext(),
            "Failed to scan.",
            Toast.LENGTH_SHORT
        ).show()

        e.printStackTrace()
    }

    override fun onPause() {
        super.onPause()
        mCameraProvider?.unbindAll()
    }
}