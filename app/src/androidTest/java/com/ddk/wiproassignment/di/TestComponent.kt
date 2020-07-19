package com.ddk.wiproassignment.di

import com.ddk.wiproassignment.di.component.ApplicationComponent
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationTestModule::class])
interface TestComponent: ApplicationComponent {
}