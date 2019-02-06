package ru.potelov.headsandhands

import android.app.Application
import ru.potelov.headsandhands.di.provideGson
import ru.potelov.headsandhands.di.provideRemoteService
import ru.potelov.headsandhands.di.provideRepository
import timber.log.Timber

class App : Application() {

    val gson by lazy(::provideGson)
    val repository by lazy(::provideRepository)
    val remoteService by lazy(::provideRemoteService)

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}