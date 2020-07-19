package com.ddk.wiproassignment.util.rx

import com.ddk.wiproassignment.utils.rx.SchedulerProvider
import io.reactivex.Scheduler
import io.reactivex.schedulers.TestScheduler

class TestSchedulerProviderUI(private val testScheduler: TestScheduler): SchedulerProvider {
    override fun io(): Scheduler = testScheduler
}