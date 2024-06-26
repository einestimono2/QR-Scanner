// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
    }
    dependencies {
        /* MY_CUSTOM: Navigation - safeargs */
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.7.7")

        /* MY_CUSTOM: Dagger Hilt */
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.51.1")
    }
}

plugins {
    id("com.android.application") version "8.2.0" apply false
    id("org.jetbrains.kotlin.android") version "1.9.23" apply false
}
