package com.td.mylevelup.components.mortgageCell

import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.td.mylevelup.R

class MortgageCellViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    val parentLayout: RelativeLayout = itemView.findViewById(R.id.mortgageRecommendationsParentLayout)
    val mortgageBackgroundImage: ImageView = itemView.findViewById(R.id.mortgageBackground)
    val mortgageRecommendationText: TextView = itemView.findViewById(R.id.mortgageRecommendationsText)

    companion object {
        @LayoutRes
        fun getLayoutId(): Int {
            return R.layout.mortgage_card_cell
        }
    }
}