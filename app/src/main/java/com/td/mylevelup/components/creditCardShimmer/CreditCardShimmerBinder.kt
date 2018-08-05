package com.td.mylevelup.components.creditCardShimmer

import android.animation.ValueAnimator
import android.view.ViewGroup
import com.facebook.shimmer.Shimmer
import com.ngam.rvabstractions.general.AbstractDataBinder

class CreditCardShimmerBinder(
        private val shouldAutoStart: Boolean = false,
        private val duration: Long = 2000L,
        private val dropOff: Float = 0.1f,
        private val repeatMode: Int = ValueAnimator.RESTART): AbstractDataBinder<CreditCardShimmerViewHolder>() {

    override fun createViewHolder(parent: ViewGroup): CreditCardShimmerViewHolder {
        return CreditCardShimmerViewHolder(getView(CreditCardShimmerViewHolder.getLayoutId(), parent))
    }

    override fun bindViewHolder(viewHolder: CreditCardShimmerViewHolder) {
        val shimmer: Shimmer = Shimmer.AlphaHighlightBuilder().setAutoStart(shouldAutoStart)
                .setDuration(duration)
                .setDropoff(dropOff)
                .setRepeatMode(repeatMode)
                .build()
        viewHolder.longCardShimmer.setShimmer(shimmer)
        viewHolder.longCardShimmer.startShimmer()

        viewHolder.creditCardShimmerView.alpha = 0.85f
    }
}