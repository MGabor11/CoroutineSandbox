package com.mgabor.coroutine.db

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Database(entities = [BeerDataModel::class], version = 1)
abstract class BeersDatabase : RoomDatabase() {

    abstract fun beersDao(): BeersDao
}

@Dao
abstract class BeersDao {

    @Query("SELECT COUNT(id) FROM beers")
    abstract fun getCount(): Flow<Int>

    @Query("SELECT * FROM beers ")
    abstract fun getBeers(): Flow<List<BeerDataModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertAll(items: List<BeerDataModel>)

    @Query("DELETE FROM beers")
    abstract fun deleteAll()

    @Transaction
    open fun updateAll(items: List<BeerDataModel>) {
        deleteAll()
        insertAll(items)
    }
}
