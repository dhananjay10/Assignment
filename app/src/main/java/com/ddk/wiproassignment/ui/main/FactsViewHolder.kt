package com.ddk.wiproassignment.ui.main

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ddk.wiproassignment.R
import com.ddk.wiproassignment.data.ResponseItem
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.list_item.*

class FactsViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
    LayoutContainer {

    fun bindData(item: ResponseItem) {
        tv_title.text = item.title ?: tv_description.context.getString(R.string.title_not_available)
        tv_description.text =
            item.description ?: tv_description.context.getString(R.string.description_not_available)
        Glide.with(iv_image.context)
            .load(item.imageHref)
            .error(R.drawable.ic_error)
            .into(iv_image)
    }

}