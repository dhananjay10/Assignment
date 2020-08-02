package com.ddk.wiproassignment.data.repositories

import com.ddk.wiproassignment.data.ResponseMaster
import com.ddk.wiproassignment.data.local.DatabaseService
import com.ddk.wiproassignment.data.local.entity.FactsMasterEntity
import com.ddk.wiproassignment.network.ApiService
import com.ddk.wiproassignment.utils.Converter
import io.reactivex.Single
import javax.inject.Inject

class FactsRepository @Inject constructor(
    private val apiService: ApiService,
    private val databaseService: DatabaseService
) {

    fun getFacts(): Single<ResponseMaster> =
        apiService
            .getFacts()
            .map { it }

    fun saveFacts(responseMaster: ResponseMaster) {
        val factsMasterEntity = FactsMasterEntity(
            responseMaster.title,
            Converter().fromListToString(responseMaster.responseList)
        )
        databaseService.factsDao().insertFacts(factsMasterEntity)
    }

}