package com.sample.radiostations.core.commons.impl.dispatcher.di

import com.sample.radiostations.core.commons.api.dispatcher.CoroutineDispatchers
import com.sample.radiostations.core.commons.api.dispatcher.DefaultDispatcher
import com.sample.radiostations.core.commons.api.dispatcher.IoDispatcher
import com.sample.radiostations.core.commons.api.dispatcher.MainDispatcher
import com.sample.radiostations.core.commons.impl.dispatcher.CoroutineDispatchersImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {
    @Binds
    fun bindsCoroutineDispatchers(impl: CoroutineDispatchersImpl): CoroutineDispatchers

    @Module
    @InstallIn(SingletonComponent::class)
    object Provider {
        @Provides
        @Singleton
        @MainDispatcher
        fun mainDispatcher(): CoroutineDispatcher = Dispatchers.Main

        @Provides
        @Singleton
        @IoDispatcher
        fun ioDispatcher(): CoroutineDispatcher = Dispatchers.IO

        @Provides
        @Singleton
        @DefaultDispatcher
        fun defaultDispatcher(): CoroutineDispatcher = Dispatchers.Default
    }
}
