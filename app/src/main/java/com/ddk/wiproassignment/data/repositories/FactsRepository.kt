package com.ddk.wiproassignment.data.repositories

import com.ddk.wiproassignment.data.ResponseMaster
import com.ddk.wiproassignment.network.ApiService
import com.ddk.wiproassignment.utils.network.NetworkHelper
import io.reactivex.Single
import javax.inject.Inject

class FactsRepository @Inject constructor(
    private val apiService: ApiService,
    private val networkHelper: NetworkHelper
) {

    fun getFacts(): Single<ResponseMaster> =
        apiService
            .getFacts()
            .map { it }
}