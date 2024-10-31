package com.sample.radiostations.di

import com.sample.radiostations.BuildConfig
import com.sample.radiostations.core.commons.api.provider.BuildConfigProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {
    @Provides
    @Singleton
    fun buildConfig(): BuildConfigProvider = BuildConfigProvider(
        baseUrl = BuildConfig.BASE_URL,
        token = BuildConfig.API_KEY,
        debug = BuildConfig.DEBUG
    )
}
