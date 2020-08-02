package com.ddk.wiproassignment

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.ddk.wiproassignment.data.local.DatabaseService
import com.ddk.wiproassignment.data.local.entity.FactsMasterEntity
import io.reactivex.Flowable
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.testng.Assert

@RunWith(AndroidJUnit4::class)
class FactsDaoTest {

    private lateinit var databaseService: DatabaseService

    @Mock
    private lateinit var factsMasterEntity: FactsMasterEntity

    private lateinit var flowableEntity: Flowable<FactsMasterEntity>

    @Before
    fun initDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        databaseService = Room.inMemoryDatabaseBuilder(context, DatabaseService::class.java).build()
    }

    @After
    fun closeDb() {
        databaseService.close()
    }

    @Test
    fun insertFacts() {
        databaseService.factsDao().insertFacts(factsMasterEntity)
        flowableEntity = databaseService.factsDao().getAllFacts()
        Assert.assertNotNull(flowableEntity)
    }
}