package com.ddk.wiproassignment.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ddk.wiproassignment.data.local.entity.FactsMasterEntity
import io.reactivex.Flowable

@Dao
interface FactsDao {

    @Query("SELECT * FROM facts_master_entity")
    fun getAllFacts(): Flowable<FactsMasterEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFacts(entity: FactsMasterEntity)
}