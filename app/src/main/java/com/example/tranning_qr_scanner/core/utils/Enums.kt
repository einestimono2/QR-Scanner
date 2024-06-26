package com.example.tranning_qr_scanner.core.utils

enum class TransitionType {
    RTL,
    LTR,
    FADE,
    NONE
}

enum class BarcodeType {
    UNKNOWN,
    TEXT,
    URL,
    WIFI,
    EMAIL,
    PHONE,
    ISBN,
    GEO,
    CONTACT,
    CALENDAR,
    PRODUCT,
    SMS,
    DRIVER_LICENSE
}

enum class BarcodeFormat {
    UNKNOWN,
    QR_CODE,
    EAN_13,
    EAN_8,
    UPC_A,
    UPC_E,
    CODE_39,
    CODE_93,
    CODE_128,
    PDF417,
    DATA_MATRIX,
    AZTEC,
    ITF,
    CODABAR
}

enum class WifiSecurity {
    OPEN,
    WEP,
    WPA,
    WPA2
}

enum class ContactType {
    MOBILE,
    WORK,
    HOME,
    OTHER
}