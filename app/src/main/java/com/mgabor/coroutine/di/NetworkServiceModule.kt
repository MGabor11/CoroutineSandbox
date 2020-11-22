package com.mgabor.coroutine.di

import com.mgabor.coroutine.api.BeerServiceApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class NetworkServiceModule {

    @Provides
    @Singleton
    fun provideBeerService() =
        Retrofit.Builder()
            .baseUrl(BEERS_SERVICE_BASE_URL)
            .client(
                OkHttpClient.Builder()
                    .readTimeout(TIMEOUT, TimeUnit.MILLISECONDS)
                    .writeTimeout(TIMEOUT, TimeUnit.MILLISECONDS)
                    .build()
            )
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BeerServiceApi::class.java)

    companion object {
        private const val BEERS_SERVICE_BASE_URL = "https://api.punkapi.com/v2/"
        private const val TIMEOUT = 15000L
    }
}
