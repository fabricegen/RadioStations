package com.sample.radiostations.core.commons.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

abstract class BaseViewModel<Action, State>(initialState: State) : ViewModel() {
    private val _uiState = MutableStateFlow(initialState)
    val uiState = _uiState.asStateFlow()

    protected fun updateState(reducer: State.() -> State) {
        _uiState.value = reducer(_uiState.value)
    }

    abstract fun handle(action: Action)

    open fun destroy() {
        super.onCleared()
    }
}
