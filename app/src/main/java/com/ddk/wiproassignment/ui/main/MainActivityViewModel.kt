package com.ddk.wiproassignment.ui.main

import androidx.lifecycle.MutableLiveData
import com.ddk.wiproassignment.base.BaseViewModel
import com.ddk.wiproassignment.data.ResponseMaster
import com.ddk.wiproassignment.data.repositories.FactsRepository
import com.ddk.wiproassignment.utils.network.NetworkHelper
import com.ddk.wiproassignment.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

class MainActivityViewModel(
    schedulerProvider: SchedulerProvider,
    compositeDisposable: CompositeDisposable,
    networkHelper: NetworkHelper,
    private val factsRepository: FactsRepository
) : BaseViewModel(schedulerProvider, compositeDisposable, networkHelper) {

    val responseData = MutableLiveData<ResponseMaster>()
    val errorData = MutableLiveData<String>()

    override fun onCreate() {

    }

    fun getFacts() {
        if (checkNetworkConnection()) {
            compositeDisposable.addAll(
                factsRepository.getFacts()
                    .subscribeOn(schedulerProvider.io())
                    .subscribe(
                        {
                            responseData.postValue(it)
                        }, {
                            handleNetworkError(it)
                        }
                    )
            )
        }
    }
}