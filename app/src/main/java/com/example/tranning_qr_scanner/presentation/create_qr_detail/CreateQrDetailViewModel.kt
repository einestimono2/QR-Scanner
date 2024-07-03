package com.example.tranning_qr_scanner.presentation.create_qr_detail

import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tranning_qr_scanner.core.utils.SingleLiveEvent

class CreateQrDetailViewModel : ViewModel() {
    var canCreate = MutableLiveData<Boolean>()

    private val _bitmap = MutableLiveData<Bitmap>()
    val bitmap: LiveData<Bitmap>
        get() = _bitmap

    private var _isQRGeneratedSuccessfully = SingleLiveEvent<Boolean>()
    val isQRGeneratedSuccessfully: SingleLiveEvent<Boolean>
        get() = _isQRGeneratedSuccessfully

    private var _imageSaved = SingleLiveEvent<Boolean>()
    val imageSaved: SingleLiveEvent<Boolean>
        get() = _imageSaved
}