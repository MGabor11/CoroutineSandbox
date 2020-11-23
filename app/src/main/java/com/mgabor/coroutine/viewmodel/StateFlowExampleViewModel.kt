package com.mgabor.coroutine.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*

open class StateFlowExampleViewModel @ViewModelInject internal constructor() : ViewModel() {

    private val countInnerState = MutableStateFlow(0)

    val countState: StateFlow<Int> = countInnerState

    val countStateFlow: Flow<Int> = flow {
        for (i in 1..MAX_EMITTED_VALUE) {
            delay(1000)
            emit(i)
        }
    }.shareIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        replay = MAX_EMITTED_VALUE
    )

    fun incrementCount() {
        countInnerState.value++
    }

    fun decrementCount() {
        countInnerState.value--
    }

    companion object {
        private const val MAX_EMITTED_VALUE = 3
    }
}
