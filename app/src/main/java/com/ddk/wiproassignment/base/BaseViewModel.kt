package com.ddk.wiproassignment.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ddk.wiproassignment.R
import com.ddk.wiproassignment.utils.network.NetworkHelper
import com.ddk.wiproassignment.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel(
    protected val schedulerProvider: SchedulerProvider,
    protected val compositeDisposable: CompositeDisposable,
    protected val networkHelper: NetworkHelper
) : ViewModel() {

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

    val messageStringId: MutableLiveData<Int> = MutableLiveData()
    protected fun checkNetworkConnection(): Boolean =
        if (networkHelper.isNetworkConnected()) {
            true
        } else {
            messageStringId.postValue(R.string.network_error)
            false
        }

    abstract fun onCreate()

    protected fun handleNetworkError(throwable: Throwable) =
        throwable.let {
            networkHelper.castToNetworkError(it).run {
                when (status) {
                    -1 -> messageStringId.postValue(R.string.network_default_error)
                    0 -> messageStringId.postValue(R.string.server_connection_error)
                }
            }
        }
}