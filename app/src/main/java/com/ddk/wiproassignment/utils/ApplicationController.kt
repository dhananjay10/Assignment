package com.ddk.wiproassignment.utils

import android.app.Application
import com.ddk.wiproassignment.di.component.ApplicationComponent
import com.ddk.wiproassignment.di.component.DaggerApplicationComponent
import com.ddk.wiproassignment.di.module.ApplicationModule

class ApplicationController:Application() {

    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        injectDependencies()
    }

    private fun injectDependencies() {
        applicationComponent = DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .build()
        applicationComponent.inject(this)
    }

    // Needed to replace the component with a test specific one
    fun setComponent(applicationComponent: ApplicationComponent) {
        this.applicationComponent = applicationComponent
    }
}