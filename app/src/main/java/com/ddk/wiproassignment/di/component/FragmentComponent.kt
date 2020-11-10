package com.ddk.wiproassignment.di.component

import com.ddk.wiproassignment.ui.main.FactsFragment
import com.ddk.wiproassignment.di.FragmentScope
import com.ddk.wiproassignment.di.module.FragmentModule
import dagger.Component

@FragmentScope
@Component(
    dependencies = [ApplicationComponent::class],
    modules = [FragmentModule::class]
)
interface FragmentComponent {

    fun inject(fragment: FactsFragment)
}