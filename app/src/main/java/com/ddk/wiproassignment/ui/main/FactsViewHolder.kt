package com.ddk.wiproassignment.ui.main

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ddk.wiproassignment.R
import com.ddk.wiproassignment.data.ResponseItem

class FactsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private var tvTitle: TextView = itemView.findViewById(R.id.tv_title);
    private var tvDescription: TextView = itemView.findViewById(R.id.tv_description);
    private var ivImage: ImageView = itemView.findViewById(R.id.iv_image);

    fun bindData(item: ResponseItem) {
        tvTitle.text = item.title ?: tvDescription.context.getString(R.string.title_not_available)
        tvDescription.text = item.description  ?: tvDescription.context.getString(R.string.description_not_available)
        Glide.with(ivImage.context)
            .load(item.imageHref)
            .error(R.drawable.ic_error)
            .into(ivImage)
    }

}