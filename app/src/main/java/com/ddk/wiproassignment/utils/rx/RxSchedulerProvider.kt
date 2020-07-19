package com.ddk.wiproassignment.utils.rx

import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

class RxSchedulerProvider : SchedulerProvider {
    override fun io(): Scheduler = Schedulers.io()
}