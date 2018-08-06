package com.td.mylevelup.components.investingCell

import android.support.annotation.DrawableRes
import android.view.View
import android.view.ViewGroup
import com.ngam.rvabstractions.general.AbstractDataBinder
import com.td.mylevelup.R

class InvestingCellBinder(private val recommendationsText: String,
                          @DrawableRes private val backgroundImageResID: Int = R.drawable.investing_banner_1,
                          private val parentClickListener: View.OnClickListener = View.OnClickListener {  }):
        AbstractDataBinder<InvestingCellViewHolder>() {

    override fun createViewHolder(parent: ViewGroup): InvestingCellViewHolder {
        return InvestingCellViewHolder(getView(InvestingCellViewHolder.getLayoutId(), parent))
    }

    override fun bindViewHolder(viewHolder: InvestingCellViewHolder) {
        viewHolder.parentLayout.setOnClickListener(parentClickListener)
        viewHolder.investingBackgroundImage.setImageResource(backgroundImageResID)
        viewHolder.investingRecommendationText.text = recommendationsText
    }
}