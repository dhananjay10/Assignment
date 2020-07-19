package com.ddk.wiproassignment.ui.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.ddk.wiproassignment.R
import com.ddk.wiproassignment.data.ResponseItem
import com.ddk.wiproassignment.data.ResponseMaster
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
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
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
    private lateinit var errorObserver: Observer<String>

    @Mock
    private lateinit var messageStringIdObserver: Observer<Int>

    private lateinit var testScheduler: TestScheduler

    private lateinit var mainActivityViewModel: MainActivityViewModel

    @Mock
    private lateinit var responseMaster: ResponseMaster

    @Mock
    private lateinit var responseItem: ResponseItem

    @Before
    fun setUp() {
        val compositeDisposable = CompositeDisposable()
        testScheduler = TestScheduler()
        val testSchedulerProvider = TestSchedulerProvider(testScheduler)
        mainActivityViewModel = MainActivityViewModel(
            testSchedulerProvider,
            compositeDisposable,
            networkHelper,
            factsRepository
        )
        
        mainActivityViewModel.responseData.observeForever(responseObserver)
        mainActivityViewModel.errorData.observeForever(errorObserver)
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
    fun `test get facts error`(){
        doReturn(false)
            .`when`(networkHelper)
            .isNetworkConnected()
        mainActivityViewModel.getFacts()
        assert(mainActivityViewModel.messageStringId.value == R.string.network_error)
    }

    @After
    fun tearDown(){
        mainActivityViewModel.responseData.removeObserver(responseObserver)
    }
}