package com.ddk.wiproassignment.di.module

import android.app.Application
import android.content.Context
import com.ddk.wiproassignment.di.ApplicationContext
import com.ddk.wiproassignment.network.ApiClient
import com.ddk.wiproassignment.network.ApiService
import com.ddk.wiproassignment.network.UrlConstants
import com.ddk.wiproassignment.utils.ApplicationController
import com.ddk.wiproassignment.utils.network.NetworkHelper
import com.ddk.wiproassignment.utils.rx.RxSchedulerProvider
import com.ddk.wiproassignment.utils.rx.SchedulerProvider
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: ApplicationController) {

    @Provides
    @Singleton
    fun provideApplication(): Application = application

    @Provides
    @Singleton
    @ApplicationContext
    fun provideContext(): Context = application

    /**
     * Since this function do not have @Singleton then each time CompositeDisposable is injected
     * then a new instance of CompositeDisposable will be provided
     */
    @Provides
    fun provideCompositeDisposable(): CompositeDisposable = CompositeDisposable()

    @Provides
    fun provideSchedulerProvider(): SchedulerProvider = RxSchedulerProvider()

    @Provides
    @Singleton
    fun provideApiClient(): ApiService = ApiClient.create(UrlConstants.BASE_URL)

    @Singleton
    @Provides
    fun provideNetworkHelper(): NetworkHelper =
        NetworkHelper(application)
}