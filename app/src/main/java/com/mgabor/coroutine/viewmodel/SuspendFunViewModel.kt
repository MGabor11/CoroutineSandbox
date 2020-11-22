package com.mgabor.coroutine.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mgabor.coroutine.api.BeerServiceApi
import com.mgabor.coroutine.data.Beer
import com.mgabor.coroutine.util.launchOnDefault
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filter

class SuspendFunViewModel @ViewModelInject internal constructor(
    private val beerServiceApi: BeerServiceApi
) : ViewModel() {

    private val infoInnerState = MutableStateFlow("")

    val infoState: StateFlow<String> = infoInnerState

    val beers: MutableLiveData<List<Beer>> = MutableLiveData(emptyList())

    init {
        viewModelScope.launchOnDefault {
            val beerList = beerServiceApi.getBeers()
            beers.postValue(beerList.map { Beer(id = it.id, name = it.name) })
        }
    }

    fun doStuff() {
        viewModelScope.launchOnDefault {
            sendInfo("Something1")
            sendInfo("Something2")
            sendInfo("Something3")
        }
    }

    private suspend fun sendInfo(text: String) {
        infoInnerState.value = text
        delay(2000)
    }
}
