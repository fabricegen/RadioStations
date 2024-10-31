package com.radiostations.app.data.api

import com.sample.radiostations.core.commons.api.provider.BuildConfigProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal class NetworkModule {

    @Provides
    fun okHttpClientBuilder(
        buildConfigProvider: BuildConfigProvider
    ): OkHttpClient.Builder {
        return OkHttpClient.Builder()
            .run {
                if (buildConfigProvider.debug) {
                    val loggingInterceptor = HttpLoggingInterceptor().apply {
                        level = HttpLoggingInterceptor.Level.BODY
                    }
                    addInterceptor(loggingInterceptor)
                }
                this
            }.addInterceptor(HeadersInterceptors(buildConfigProvider.token))
    }

    @Provides
    fun provideRetrofitBuilder(): Retrofit.Builder {
        val contentType = "application/json".toMediaType()
        val json = Json { ignoreUnknownKeys = true }
        return Retrofit.Builder()
            .addConverterFactory(json.asConverterFactory(contentType))
    }

    @Provides
    @Singleton
    fun provideRadiosApiService(
        okHttpClient: OkHttpClient.Builder,
        retrofitBuilder: Retrofit.Builder,
        buildConfigProvider: BuildConfigProvider
    ): RadiosApiService {
        val retrofit = retrofitBuilder
            .baseUrl(buildConfigProvider.baseUrl)
            .client(okHttpClient.build())
            .build()

        return retrofit.create(RadiosApiService::class.java)
    }
}
