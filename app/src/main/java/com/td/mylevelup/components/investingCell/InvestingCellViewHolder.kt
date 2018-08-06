package com.td.mylevelup.components.investingCell

import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.td.mylevelup.R

class InvestingCellViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    val parentLayout: RelativeLayout = itemView.findViewById(R.id.investingRecommendationsParentLayout)
    val investingBackgroundImage: ImageView = itemView.findViewById(R.id.investingBackground)
    val investingRecommendationText: TextView = itemView.findViewById(R.id.investingRecommendationsText)
    companion object {
        @LayoutRes
        fun getLayoutId(): Int {
            return R.layout.investing_card_cell
        }
    }
}