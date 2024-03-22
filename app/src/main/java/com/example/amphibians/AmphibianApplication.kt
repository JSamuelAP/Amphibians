package com.example.amphibians

import android.app.Application
import com.example.amphibians.data.AppContainer
import com.example.amphibians.data.DefaultAppContainer

class AmphibianApplication : Application() {
    /** AppContainer instance used by the rest of classes to obtain dependencis */
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}