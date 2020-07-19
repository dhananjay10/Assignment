package com.ddk.wiproassignment.di.component

import com.ddk.wiproassignment.di.ActivityScope
import com.ddk.wiproassignment.di.module.ActivityModule
import com.ddk.wiproassignment.ui.main.MainActivity
import dagger.Component

@ActivityScope
@Component(
    dependencies = [ApplicationComponent::class],
    modules = [ActivityModule::class]
)
interface ActivityComponent {
    fun inject(activity: MainActivity)
}