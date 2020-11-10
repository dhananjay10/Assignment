package com.ddk.wiproassignment.di.module

import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.ddk.wiproassignment.adapter.FactsAdapter
import com.ddk.wiproassignment.base.BaseFragment
import com.ddk.wiproassignment.data.local.DatabaseService
import com.ddk.wiproassignment.data.repositories.FactsRepository
import com.ddk.wiproassignment.ui.main.FactsViewModel
import com.ddk.wiproassignment.utils.ViewModelProviderFactory
import com.ddk.wiproassignment.utils.network.NetworkHelper
import com.ddk.wiproassignment.utils.rx.SchedulerProvider
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
class FragmentModule(private val fragment: BaseFragment<*>) {

    @Provides
    fun provideLinearLayoutManager(): LinearLayoutManager = LinearLayoutManager(fragment.context)

    @Provides
    fun provideFactsAdapter(): FactsAdapter = FactsAdapter()

    @Provides
    fun provideMainActivityViewModel(
        schedulerProvider: SchedulerProvider,
        compositeDisposable: CompositeDisposable,
        networkHelper: NetworkHelper,
        factsRepository: FactsRepository,
        databaseService: DatabaseService
    ): FactsViewModel = ViewModelProviders.of(fragment,
        ViewModelProviderFactory(FactsViewModel::class) {
            FactsViewModel(
                schedulerProvider,
                compositeDisposable,
                networkHelper,
                factsRepository,
                databaseService
            )
        }).get(FactsViewModel::class.java)
}