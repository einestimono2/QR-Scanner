plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")

    /* MY_CUSTOM: binding */
    id("kotlin-kapt")
}

android {
    namespace = "com.example.tranning_qr_scanner"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.tranning_qr_scanner"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    /* MY_CUSTOM: binding */
    buildFeatures {
        dataBinding = true
        viewBinding = true
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.13.1")
    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("com.google.android.material:material:1.12.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    /* MY_CUSTOM: Qrcode & barcodes */
    implementation("com.google.mlkit:barcode-scanning:17.2.0")

    /* MY_CUSTOM: Navigation */
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.7")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.7")
    implementation("androidx.navigation:navigation-dynamic-features-fragment:2.7.7")

    /* MY_CUSTOM: Activity */
    implementation("androidx.activity:activity-ktx:1.9.0")

    /* MY_CUSTOM: ViewModel */
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.1")
}