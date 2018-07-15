package com.gmail.martsulgp.qulixsystemstestapp.di

import android.app.Application
import com.gmail.martsulgp.qulixsystemstestapp.data.GifsDataRequest
import com.gmail.martsulgp.qulixsystemstestapp.data.GifsDataRequestImpl
import org.koin.android.ext.android.startKoin
import org.koin.dsl.module.applicationContext

class QulixSystemsTestApplication : Application() {
    private val QulixSystemsTestApplicationModules = applicationContext {
        bean { GifsDataRequestImpl() as GifsDataRequest }
    }

    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(QulixSystemsTestApplicationModules))
    }
}