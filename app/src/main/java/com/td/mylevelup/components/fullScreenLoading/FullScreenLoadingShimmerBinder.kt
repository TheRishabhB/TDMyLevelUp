package com.td.mylevelup.components.fullScreenLoading

import android.view.ViewGroup
import com.ngam.rvabstractions.general.AbstractDataBinder

class FullScreenLoadingShimmerBinder: AbstractDataBinder<FullScreenLoadingShimmerViewHolder>() {
    override fun createViewHolder(parent: ViewGroup): FullScreenLoadingShimmerViewHolder {
        return FullScreenLoadingShimmerViewHolder(getView(FullScreenLoadingShimmerViewHolder.getLayoutId(), parent))
    }

    override fun bindViewHolder(viewHolder: FullScreenLoadingShimmerViewHolder) {
        // Not needed at the moment.
    }
}