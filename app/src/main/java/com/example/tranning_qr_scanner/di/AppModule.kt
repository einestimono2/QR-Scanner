package com.example.tranning_qr_scanner.di

import android.app.Application
import com.example.tranning_qr_scanner.core.utils.helper.AppCache
import com.example.tranning_qr_scanner.core.utils.helper.CacheHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideAppCacheManager(app: Application): AppCache {
        return CacheHelper(app)
    }
}