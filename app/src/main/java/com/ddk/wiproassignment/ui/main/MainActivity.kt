package com.ddk.wiproassignment.ui.main

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.ddk.wiproassignment.R
import com.ddk.wiproassignment.adapter.FactsAdapter
import com.ddk.wiproassignment.base.BaseActivity
import com.ddk.wiproassignment.data.ResponseMaster
import com.ddk.wiproassignment.di.component.ActivityComponent
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity<MainActivityViewModel>() {

    @Inject
    lateinit var linearLayoutManager: LinearLayoutManager

    @Inject
    lateinit var factsAdapter: FactsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun provideLayoutId(): Int = R.layout.activity_main

    override fun injectDependencies(activityComponent: ActivityComponent) {
        activityComponent.inject(this)
    }

    override fun setupView(savedInstanceState: Bundle?) {
        viewModel.getFacts()
        rv_facts.apply {
            addItemDecoration(DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL))
            layoutManager = linearLayoutManager
            adapter = factsAdapter
        }
        swipe_refresh_layout.setOnRefreshListener {
            viewModel.getFacts()
        }
    }

    override fun setupObservers() {
        super.setupObservers()
        viewModel.responseData.observe(this, Observer<ResponseMaster> {
            toolbar.setTitle(it.title)
            swipe_refresh_layout.isRefreshing = false
            factsAdapter.apply {
                this.updateList(it.responseList.filterNotNull())
                notifyDataSetChanged()
            }
        })

        viewModel.messageStringId.observe(this, Observer<Int> {
            showMessage(it)
            swipe_refresh_layout.isRefreshing = false
        })
    }
}
