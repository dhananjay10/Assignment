package com.ddk.wiproassignment.ui.main

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.room.Room
import com.ddk.wiproassignment.data.ResponseMaster
import com.ddk.wiproassignment.data.local.DatabaseService
import com.ddk.wiproassignment.data.repositories.FactsRepository
import com.ddk.wiproassignment.util.rx.TestSchedulerProvider
import com.ddk.wiproassignment.utils.network.NetworkHelper
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.TestScheduler
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.doReturn
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner.Silent::class)
class MainActivityViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var networkHelper: NetworkHelper

    @Mock
    private lateinit var factsRepository: FactsRepository

    @Mock
    private lateinit var responseObserver: Observer<ResponseMaster>

    @Mock
    private lateinit var messageStringIdObserver: Observer<Int>

    private lateinit var testScheduler: TestScheduler

    private lateinit var mainActivityViewModel: MainActivityViewModel

    @Mock
    private lateinit var responseMaster: ResponseMaster

    private lateinit var databaseService: DatabaseService

    @Mock
    private lateinit var context: Context

    @Before
    fun setUp() {
        val compositeDisposable = CompositeDisposable()
        testScheduler = TestScheduler()
        val testSchedulerProvider = TestSchedulerProvider(testScheduler)
        databaseService = Room.inMemoryDatabaseBuilder(context, DatabaseService::class.java).build()
        mainActivityViewModel = MainActivityViewModel(
            testSchedulerProvider,
            compositeDisposable,
            networkHelper,
            factsRepository,
            databaseService
        )
        mainActivityViewModel.responseData.observeForever(responseObserver)
    }

    @Test
    fun `test get facts success`() {
        doReturn(true)
            .`when`(networkHelper).isNetworkConnected()
        doReturn(Single.just(responseMaster))
            .`when`(factsRepository).getFacts()
        mainActivityViewModel.getFacts()
        testScheduler.triggerActions()
        verify(responseObserver).onChanged(responseMaster)
    }

    @Test
    fun `test get facts error`() {
        doReturn(false)
            .`when`(networkHelper)
            .isNetworkConnected()
        mainActivityViewModel.getFactsFromCache()
        testScheduler.triggerActions()
        databaseService.factsDao().getAllFacts()
    }

    @After
    fun tearDown() {
        mainActivityViewModel.responseData.removeObserver(responseObserver)
        mainActivityViewModel.messageStringId.removeObserver(messageStringIdObserver)
    }
}