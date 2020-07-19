package com.ddk.wiproassignment.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ResponseMaster(
    @Expose
    @SerializedName("title")
    var title: String,

    @Expose
    @SerializedName("rows")
    var responseList: ArrayList<ResponseItem>
)