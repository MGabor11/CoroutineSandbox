package com.mgabor.coroutine.viewmodel

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mgabor.coroutine.util.launchOnDefault
import kotlinx.coroutines.*

class ErrorHandlingViewModel @ViewModelInject internal constructor() : ViewModel() {

    private val coroutineExceptionHandler =
        CoroutineExceptionHandler { _, exception ->
            println("Handle $exception in CoroutineExceptionHandler")
        }

    private val topLevelScope = CoroutineScope(Job())

    private val asyncScope = CoroutineScope(Job())

    fun withSupervisorScope() {
        topLevelScope.launch {
            val job1 = launch {
                delay(3000)
                Log.d("ERROR_HANDLING", "starting Coroutine 1")
            }

            supervisorScope {
                val job2 = launch(coroutineExceptionHandler) {
                    delay(1000)
                    Log.d("ERROR_HANDLING", "starting Coroutine 2")
                    throw RuntimeException("Exception in Coroutine 2")
                }

                val job3 = launch {
                    delay(3000)
                    Log.d("ERROR_HANDLING", "starting Coroutine 3")
                }

                listOf(job2, job3).joinAll()
                Log.d("ERROR_HANDLING", "Inner scope ending")
            }
        }
    }

    fun withBasicScopeStructure() {
        topLevelScope.launch(coroutineExceptionHandler) {
            val job1 = launch {
                Log.d("ERROR_HANDLING", "starting Coroutine 1")
                delay(3000)
                Log.d("ERROR_HANDLING", "ending Coroutine 1")
            }

            val job2 = launch {
                Log.d("ERROR_HANDLING", "starting Coroutine 2")
                delay(1000)
                throw RuntimeException("Exception in Coroutine 2")
            }

            val job3 = launch {
                Log.d("ERROR_HANDLING", "starting Coroutine 3")
                delay(3000)
                Log.d("ERROR_HANDLING", "ending Coroutine 3")
            }

            listOf(job1, job2, job3).joinAll()
            Log.d("ERROR_HANDLING", "Scope ending")
        }
    }

    private val deferredResult = asyncScope.async<Int> {
        throw RuntimeException("RuntimeException in async coroutine")
    }

    fun doErrorWithAsync() {
        viewModelScope.launch {
            try {
                Log.d("ERROR_HANDLING", "Clicked on doErrorWithAsync button")
                delay(3000)
                Log.d("ERROR_HANDLING", "delay is over")
                deferredResult.await()
            } catch (exception: Exception) {
                Log.d("ERROR_HANDLING", "Handle $exception in try/catch")
            }
        }
    }
}
