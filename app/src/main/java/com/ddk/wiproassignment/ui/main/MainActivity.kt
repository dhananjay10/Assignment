package com.ddk.wiproassignment.ui.main

import android.os.Bundle
import com.ddk.wiproassignment.R
import com.ddk.wiproassignment.base.BaseActivity

class MainActivity : BaseActivity() {

    override fun provideLayoutId(): Int = R.layout.activity_main

    override fun setupView(savedInstanceState: Bundle?) {
        addFragment(FactsFragment.newInstance())
    }
}
