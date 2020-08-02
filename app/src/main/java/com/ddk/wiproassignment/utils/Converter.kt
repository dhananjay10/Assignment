package com.ddk.wiproassignment.utils

import androidx.room.TypeConverter
import com.ddk.wiproassignment.data.ResponseItem
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converter {

    private val gson = Gson()

    @TypeConverter
    fun fromListToString(responseItem: List<ResponseItem>): String {
        return gson.toJson(responseItem)
    }

    @TypeConverter
    fun toListFromString(responseItem: String): ArrayList<ResponseItem> {
        val type = object : TypeToken<List<ResponseItem>>() {}.type
        return gson.fromJson(responseItem, type)
    }
}