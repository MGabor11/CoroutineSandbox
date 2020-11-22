package com.mgabor.coroutine.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

open class StateFlowExampleViewModel @ViewModelInject internal constructor() : ViewModel() {

    private val countInnerState = MutableStateFlow(0)

    val countState: StateFlow<Int> = countInnerState

    fun incrementCount() {
        countInnerState.value++
    }

    fun decrementCount() {
        countInnerState.value--
    }
}
