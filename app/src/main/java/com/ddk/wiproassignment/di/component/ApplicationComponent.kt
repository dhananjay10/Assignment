package com.ddk.wiproassignment.di.component

import android.app.Application
import android.content.Context
import com.ddk.wiproassignment.data.local.DatabaseService
import com.ddk.wiproassignment.di.ApplicationContext
import com.ddk.wiproassignment.di.module.ApplicationModule
import com.ddk.wiproassignment.network.ApiService
import com.ddk.wiproassignment.utils.ApplicationController
import com.ddk.wiproassignment.utils.network.NetworkHelper
import com.ddk.wiproassignment.utils.rx.SchedulerProvider
import dagger.Component
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    fun inject(app: ApplicationController)

    fun getApplication(): Application

    @ApplicationContext
    fun getContext(): Context

    fun getApiClient(): ApiService

    fun getNetworkHelper(): NetworkHelper

    fun getSchedulerProvider(): SchedulerProvider

    fun getCompositeDisposable(): CompositeDisposable

    fun getDatabaseService(): DatabaseService
}