package com.mgabor.coroutine.service


import com.mgabor.coroutine.api.BeerServiceApi
import com.mgabor.coroutine.data.Beer
import com.mgabor.coroutine.db.BeerDataModel
import com.mgabor.coroutine.db.BeersDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BeerServiceImpl @Inject constructor(
    private val beerServiceApi: BeerServiceApi,
    private val beersDao: BeersDao
): BeerService {

    override suspend fun refresh() = withContext(Dispatchers.Default) {
        if (beersDao.getCount().first() == 0) {
            val list = beerServiceApi.getBeers()
            beersDao.insertAll(list.map { BeerDataModel(id = it.id, name = it.name) })
        }
    }

    override fun getBeerList(): Flow<List<Beer>> = flow {
        val fooList = beerServiceApi.getBeers()
            .map {
                Beer(id = it.id, name = it.name)
            }

        emit(fooList)
    }.flowOn(Dispatchers.Default)

    override fun getStoredBeerList(): Flow<List<Beer>> = beersDao.getBeers().map { list ->
        list.map { Beer(id = it.id, name = it.name) }
    }
        .flowOn(Dispatchers.Default)
}
