package com.mgabor.coroutine.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.mgabor.coroutine.service.BeerService
import com.mgabor.coroutine.data.Beer
import com.mgabor.coroutine.util.launchOnDefault
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.onEach

class DataBaseExampleViewModel @ViewModelInject internal constructor(
    private val beerService: BeerService
) : ViewModel() {

    val loading: MutableLiveData<Boolean> = MutableLiveData<Boolean>().apply { value = true }

    val beers: LiveData<List<Beer>> = beerService.getStoredBeerList()
        .onEach {
            if (it.isNotEmpty()) {
                loading.postValue(false)
            }
        }
        .asLiveData(Dispatchers.Default)

    init {
        viewModelScope.launchOnDefault {
            beerService.refresh()
        }
    }
}