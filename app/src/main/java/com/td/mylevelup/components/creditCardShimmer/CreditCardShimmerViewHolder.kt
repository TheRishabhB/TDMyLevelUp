package com.td.mylevelup.components.creditCardShimmer

import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.view.View
import com.facebook.shimmer.ShimmerFrameLayout
import com.td.mylevelup.R

class CreditCardShimmerViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    val longCardShimmer: ShimmerFrameLayout = itemView.findViewById(R.id.longCardShimmer)
    val creditCardShimmerView: View = itemView.findViewById(R.id.creditCardShimmerView)
    companion object {
        @LayoutRes
        fun getLayoutId(): Int {
            return R.layout.long_card_shimmer
        }
    }
}