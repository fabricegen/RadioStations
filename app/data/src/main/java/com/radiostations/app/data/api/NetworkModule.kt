package com.radiostations.app.data.api

import com.radiostations.app.data.api.exception.ApiExceptionCallAdapterFactory
import com.sample.radiostations.core.commons.api.provider.ApiConfigProvider
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
        apiConfigProvider: ApiConfigProvider
    ): OkHttpClient.Builder {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(HeadersInterceptors(apiConfigProvider.token))
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
        apiConfigProvider: ApiConfigProvider
    ): RadiosApiService {
        retrofitBuilder.addCallAdapterFactory(ApiExceptionCallAdapterFactory())

        val retrofit = retrofitBuilder
            .baseUrl(apiConfigProvider.baseUrl)
            .client(okHttpClient.build())
            .build()

        return retrofit.create(RadiosApiService::class.java)
    }
}
