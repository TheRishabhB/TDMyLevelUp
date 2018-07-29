package com.td.mylevelup.components.accountsRecommendationRow

import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.td.mylevelup.R

class AccountsRecommendationRowViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    val recommendationsText: TextView = itemView.findViewById(R.id.recommendationText)
    val learnMoreButton: Button = itemView.findViewById(R.id.recommendationButton)
    companion object {
        @LayoutRes
        fun getLayoutId(): Int {
            return R.layout.accounts_recommendation_row
        }
    }
}