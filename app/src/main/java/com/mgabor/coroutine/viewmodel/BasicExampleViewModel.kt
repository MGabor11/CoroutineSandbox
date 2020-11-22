package com.mgabor.coroutine.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.mgabor.coroutine.api.BeerServiceApi
import com.mgabor.coroutine.api.BeerServiceException
import com.mgabor.coroutine.data.Beer
import kotlinx.coroutines.Dispatchers

class BasicExampleViewModel @ViewModelInject internal constructor(
    private val beerServiceApi: BeerServiceApi
) : ViewModel() {

    val loading: MutableLiveData<Boolean> = MutableLiveData<Boolean>().apply { value = true }

    val error: MutableLiveData<String> = MutableLiveData()

    val beers: LiveData<List<Beer>> = liveData(Dispatchers.IO) {
        emit(
            try {
                beerServiceApi.getBeers()
                    .map { Beer(id = it.id, name = it.name) }
            } catch (e: BeerServiceException) {
                error.postValue(e.localizedMessage)
                emptyList()
            } finally {
                loading.postValue(false)
            }
        )
    }
}