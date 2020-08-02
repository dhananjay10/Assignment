package com.ddk.wiproassignment.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "facts_master_entity")
data class FactsMasterEntity(

        @PrimaryKey
        @ColumnInfo(name = "title")
        val title: String,

        @ColumnInfo(name = "rows")
        val responseItem: String
)