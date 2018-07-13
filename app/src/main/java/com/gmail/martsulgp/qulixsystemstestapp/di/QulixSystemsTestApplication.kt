package com.gmail.martsulgp.qulixsystemstestapp.di

import android.app.Application
import com.gmail.martsulgp.qulixsystemstestapp.data.GifsData
import com.gmail.martsulgp.qulixsystemstestapp.data.GifsDataImpl
import org.koin.android.ext.android.startKoin
import org.koin.dsl.module.applicationContext

class QulixSystemsTestApplication : Application() {
    private val QulixSystemsTestApplicationModules = applicationContext {
        bean { GifsDataImpl() as GifsData }
    }

    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(QulixSystemsTestApplicationModules))
    }
}