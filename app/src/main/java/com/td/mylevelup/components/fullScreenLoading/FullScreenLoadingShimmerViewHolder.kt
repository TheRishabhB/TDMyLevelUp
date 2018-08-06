package com.td.mylevelup.components.fullScreenLoading

import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.view.View
import com.td.mylevelup.R

class FullScreenLoadingShimmerViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    companion object {
        @LayoutRes
        fun getLayoutId(): Int {
            return R.layout.loading_shimmer
        }
    }
}