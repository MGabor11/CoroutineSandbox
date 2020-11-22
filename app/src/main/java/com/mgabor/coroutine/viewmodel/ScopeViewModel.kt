package com.mgabor.coroutine.viewmodel

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ScopeViewModel @ViewModelInject internal constructor() : ViewModel() {

    fun doStuff() {
        viewModelScope.launch {
            val job = launch {
                try {
                    repeat(1000) { i ->
                        Log.d("SCOPE", "job: I'm sleeping $i ...")
                        delay(500L)
                    }
                } finally {
                    Log.d("SCOPE", "job: I'm running finally")
                }
            }
            delay(1300L)
            Log.d("SCOPE", "jmain: I'm tired of waiting!")
            job.cancelAndJoin()
            Log.d("SCOPE", "main: Now I can quit.")
        }
    }

    fun cancelRootJob() {
        viewModelScope.launch {
            val rootJob = launch {
                val job1 = launch {
                    try {
                        repeat(1000) { i ->
                            Log.d("SCOPE", "job2: I'm sleeping $i ...")
                            delay(500L)
                        }
                    } finally {
                        Log.d("SCOPE", "job 1 quit.")
                    }
                }

                val job2 = launch {
                    try {
                        repeat(1000) { i ->
                            Log.d("SCOPE", "job1: I'm sleeping $i ...")
                            delay(300L)
                        }
                    } finally {
                        Log.d("SCOPE", "job 2 quit.")
                    }
                }
            }

            Log.d("SCOPE", "Before delay")
            delay(3000)
            Log.d("SCOPE", "After delay, cancel the root job")

            rootJob.cancel()
        }
    }
}
