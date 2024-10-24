package com.sample.radiostations.di

import com.sample.radiostations.BuildConfig
import com.sample.radiostations.core.commons.api.provider.ApiConfigProvider
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
    fun apiConfig(): ApiConfigProvider = ApiConfigProvider(
        baseUrl = BuildConfig.BASE_URL,
        token = BuildConfig.API_KEY
    )
}
