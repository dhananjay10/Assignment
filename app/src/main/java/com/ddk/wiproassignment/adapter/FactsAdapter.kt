package com.ddk.wiproassignment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ddk.wiproassignment.R
import com.ddk.wiproassignment.data.ResponseItem
import com.ddk.wiproassignment.ui.main.FactsViewHolder

class FactsAdapter : RecyclerView.Adapter<FactsViewHolder>() {

    val responseList: ArrayList<ResponseItem> = ArrayList<ResponseItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FactsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return FactsViewHolder(view)
    }

    override fun getItemCount(): Int = responseList.size

    override fun onBindViewHolder(holder: FactsViewHolder, position: Int) {
        val item = responseList[position]
        holder.bindData(item)
    }

    fun updateList(list: List<ResponseItem>) {
        responseList.clear()
        responseList.addAll(list)
    }
}