package com.gymfinder.canada.di

import android.content.Context
import com.gymfinder.canada.data.local.database.GymDatabase
import com.gymfinder.canada.data.repository.GymRepositoryImpl
import com.gymfinder.canada.domain.repository.GymRepository
import com.gymfinder.canada.util.CityDataProvider

class AppContainer(context: Context) {

    val database: GymDatabase by lazy {
        GymDatabase.getDatabase(context)
    }

    val gymRepository: GymRepository by lazy {
        GymRepositoryImpl(database.gymDao())
    }

    val cityDataProvider = CityDataProvider
}

object AppContainerProvider {
    @Volatile
    private var INSTANCE: AppContainer? = null

    fun getContainer(context: Context): AppContainer {
        return INSTANCE ?: synchronized(this) {
            INSTANCE ?: AppContainer(context.applicationContext).also { INSTANCE = it }
        }
    }
}
