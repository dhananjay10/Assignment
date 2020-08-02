package com.ddk.wiproassignment.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ddk.wiproassignment.data.local.dao.FactsDao
import com.ddk.wiproassignment.data.local.entity.FactsMasterEntity
import com.ddk.wiproassignment.utils.Converter
import javax.inject.Singleton

@Singleton
@Database(
        entities = [FactsMasterEntity::class],
        exportSchema = false,
        version = 1
)
@TypeConverters(Converter::class)
abstract class DatabaseService : RoomDatabase() {
    abstract fun factsDao(): FactsDao
}