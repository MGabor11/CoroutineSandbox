package com.mgabor.coroutine.service

import com.mgabor.coroutine.data.Beer
import kotlinx.coroutines.flow.Flow

interface BeerService {

    suspend fun refresh()

    fun getBeerList(): Flow<List<Beer>>

    fun getStoredBeerList(): Flow<List<Beer>>
}
