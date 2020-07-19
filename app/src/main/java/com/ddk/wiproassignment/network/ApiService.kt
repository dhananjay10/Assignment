package com.ddk.wiproassignment.network

import com.ddk.wiproassignment.data.ResponseMaster
import io.reactivex.Single
import retrofit2.http.GET
import javax.inject.Singleton

@Singleton
interface ApiService {

    @GET(UrlConstants.GET_FACTS)
    fun getFacts(): Single<ResponseMaster>
}