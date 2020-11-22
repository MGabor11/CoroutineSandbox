package com.mgabor.coroutine.api

import retrofit2.http.GET

interface BeerServiceApi {

    @GET("beers")
    suspend fun getBeers(): List<BeerApiModel>
}

class BeerServiceException(message: String) : RuntimeException(message)
