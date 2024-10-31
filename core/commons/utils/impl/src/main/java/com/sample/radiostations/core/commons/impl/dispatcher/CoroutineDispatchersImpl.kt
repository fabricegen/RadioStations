package com.sample.radiostations.core.commons.impl.dispatcher

import com.sample.radiostations.core.commons.api.dispatcher.CoroutineDispatchers
import com.sample.radiostations.core.commons.api.dispatcher.DefaultDispatcher
import com.sample.radiostations.core.commons.api.dispatcher.IoDispatcher
import com.sample.radiostations.core.commons.api.dispatcher.MainDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

data class CoroutineDispatchersImpl @Inject constructor(
    @IoDispatcher override val io: CoroutineDispatcher,
    @MainDispatcher override val main: CoroutineDispatcher,
    @DefaultDispatcher override val default: CoroutineDispatcher
) : CoroutineDispatchers
