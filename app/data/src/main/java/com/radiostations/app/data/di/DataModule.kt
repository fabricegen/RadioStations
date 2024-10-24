package com.radiostations.app.data.di

import com.radiostations.app.data.api.NetworkModule
import com.radiostations.app.data.repository.DefaultRadiosRepository
import com.radiostations.app.data.repository.RemoteRadiosDataSource
import com.radiostations.app.data.repository.DefaultRemoteRadiosDataSource
import com.sample.radiostations.app.domain.repository.RadiosRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)
internal interface DataModule {
    @Binds
    fun bindsRadiosRepository(impl: DefaultRadiosRepository): RadiosRepository

    @Binds
    fun bindsRemoteDataSource(impl: DefaultRemoteRadiosDataSource): RemoteRadiosDataSource
}
