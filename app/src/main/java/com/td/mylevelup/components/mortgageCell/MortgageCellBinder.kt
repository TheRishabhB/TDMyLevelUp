package com.td.mylevelup.components.mortgageCell

import android.support.annotation.DrawableRes
import android.view.View
import android.view.ViewGroup
import com.ngam.rvabstractions.general.AbstractDataBinder
import com.td.mylevelup.R

class MortgageCellBinder(private val mortgageText: String,
                         @DrawableRes private val backgroundImageResID: Int = R.drawable.home,
                         private val parentClickListener: View.OnClickListener = View.OnClickListener {  }):
        AbstractDataBinder<MortgageCellViewHolder>() {

    override fun createViewHolder(parent: ViewGroup): MortgageCellViewHolder {
        return MortgageCellViewHolder(getView(MortgageCellViewHolder.getLayoutId(), parent))
    }

    override fun bindViewHolder(viewHolder: MortgageCellViewHolder) {
        viewHolder.parentLayout.setOnClickListener(parentClickListener)
        viewHolder.mortgageBackgroundImage.setImageResource(backgroundImageResID)
        viewHolder.mortgageRecommendationText.text = mortgageText
    }
}