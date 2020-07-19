package com.ddk.wiproassignment

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.ddk.wiproassignment.data.ResponseMaster
import com.ddk.wiproassignment.data.repositories.FactsRepository
import com.ddk.wiproassignment.ui.main.MainActivity
import com.ddk.wiproassignment.ui.main.MainActivityViewModel
import com.ddk.wiproassignment.util.rx.TestSchedulerProviderUI
import com.ddk.wiproassignment.utils.network.NetworkHelper
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.TestScheduler
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentCaptor
import org.mockito.ArgumentMatchers
import org.mockito.Captor
import org.mockito.Mock
import org.mockito.Mockito.verify

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    private val component =
        TestComponentRule(InstrumentationRegistry.getInstrumentation().targetContext)

    @Mock
    private lateinit var networkHelper: NetworkHelper

    @Mock
    private lateinit var factsRepository: FactsRepository

    @Mock
    private lateinit var responseObserver: Observer<ResponseMaster>

    @Mock
    private lateinit var messageStringIdObserver: Observer<Int>


    private lateinit var mainActivityViewModel: MainActivityViewModel

    @Mock
    private lateinit var responseMaster: ResponseMaster

    @Captor
    private lateinit var responseCaptor: ArgumentCaptor<Observer<ResponseMaster>>

    @Mock
    private lateinit var responseLiveData: MutableLiveData<ResponseMaster>

    @Captor
    private lateinit var errorCaptor: ArgumentCaptor<Observer<String>>

    @Mock
    private lateinit var errorLiveData: MutableLiveData<String>

    private lateinit var testScheduler: TestScheduler

    @Rule
    @JvmField
    var activityRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    @Before
    fun setUp() {
        val compositeDisposable = CompositeDisposable()
        testScheduler = TestScheduler()
        val testSchedulerProvider = TestSchedulerProviderUI(testScheduler)
        mainActivityViewModel = MainActivityViewModel(
            testSchedulerProvider,
            compositeDisposable,
            networkHelper,
            factsRepository
        )
        activityRule.activity.viewModel = mainActivityViewModel
        verify(responseLiveData).observe(
            ArgumentMatchers.any(LifecycleOwner::class.java),
            responseCaptor.capture()
        )
        verify(errorLiveData).observe(
            ArgumentMatchers.any(LifecycleOwner::class.java),
            errorCaptor.capture()
        )
    }

    @Test
    fun testCheckViewsDisplay() {
        responseCaptor.value.onChanged(responseMaster)
        onView(withId(R.id.rv_facts))
            .check(matches(isDisplayed()))
    }


}