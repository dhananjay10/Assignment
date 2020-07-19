package com.ddk.wiproassignment.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ResponseItem(
    @Expose
    @SerializedName("title")
    var title: String,

    @Expose
    @SerializedName("description")
    var description: String,

    @Expose
    @SerializedName("imageHref")
    var imageHref: String
)