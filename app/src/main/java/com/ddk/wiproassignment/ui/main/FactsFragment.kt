package com.ddk.wiproassignment.ui.main

import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.ddk.wiproassignment.R
import com.ddk.wiproassignment.adapter.FactsAdapter
import com.ddk.wiproassignment.base.BaseFragment
import com.ddk.wiproassignment.data.ResponseMaster
import com.ddk.wiproassignment.di.component.FragmentComponent
import kotlinx.android.synthetic.main.fragment_facts.*
import javax.inject.Inject

class FactsFragment : BaseFragment<FactsViewModel>() {
    @Inject
    lateinit var linearLayoutManager: LinearLayoutManager

    @Inject
    lateinit var factsAdapter: FactsAdapter

    override fun provideLayoutId(): Int =
        R.layout.fragment_facts

    companion object {
        const val TAG = "FactsFragment"

        @JvmStatic
        fun newInstance() = FactsFragment()
    }

    override fun injectDependencies(fragmentComponent: FragmentComponent) {
        fragmentComponent.inject(this)
    }

    override fun setupView(view: View) {
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