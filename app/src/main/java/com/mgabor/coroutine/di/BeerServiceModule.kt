package com.mgabor.coroutine.di

import com.mgabor.coroutine.service.BeerService
import com.mgabor.coroutine.service.BeerServiceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
abstract class BeerServiceModule {

    @Singleton
    @Binds
    abstract fun bindBeerService(impl: BeerServiceImpl): BeerService
}
