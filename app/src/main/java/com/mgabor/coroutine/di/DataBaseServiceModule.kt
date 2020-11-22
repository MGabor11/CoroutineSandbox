package com.mgabor.coroutine.di

import android.content.Context
import androidx.room.Room
import com.mgabor.coroutine.db.BeersDao
import com.mgabor.coroutine.db.BeersDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class DataBaseServiceModule {

    @Singleton
    @Provides
    fun provideDb(@ApplicationContext context: Context): BeersDatabase {
        return Room
            .databaseBuilder(context, BeersDatabase::class.java, "beers.db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideBudgetDao(db: BeersDatabase): BeersDao {
        return db.beersDao()
    }
}