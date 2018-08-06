package com.td.mylevelup.dashboard.investingCard

import android.content.Context
import com.ngam.rvabstractions.general.AbstractAdapter
import com.td.mylevelup.R
import com.td.mylevelup.components.investingCell.InvestingCellBinder

class InvestingCardAdapter(private val context: Context,
                           private val presenter: InvestingCardPresenter): AbstractAdapter() {
    override fun buildRows() {
        listItems.clear()

        add(InvestingCellBinder(context.getString(R.string.investing_card_string),
                parentClickListener = presenter.createLaunchInvestingSearchClickListener()))

        add(InvestingCellBinder(context.getString(R.string.investing_card_string),
                parentClickListener = presenter.createLaunchInvestingSearchClickListener()))

        add(InvestingCellBinder(context.getString(R.string.investing_card_string),
                parentClickListener = presenter.createLaunchInvestingSearchClickListener()))
    }
}