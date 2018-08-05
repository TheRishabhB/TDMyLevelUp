package com.td.mylevelup.creditCard.cardRecommendation

import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.view.View
import com.td.mylevelup.R

class CreditCardRecommendationViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    val creditCardRecommendations: CreditCardRecommendationView = itemView.findViewById(R.id.creditCardRecommendationsView)
    companion object {
        @LayoutRes
        fun getLayoutId(): Int {
            return R.layout.credit_card_recommendations_viewholder
        }
    }
}