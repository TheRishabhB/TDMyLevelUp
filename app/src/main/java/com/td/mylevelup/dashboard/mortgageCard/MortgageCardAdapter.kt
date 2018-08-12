package com.td.mylevelup.dashboard.mortgageCard

import android.content.Context
import com.ngam.rvabstractions.general.AbstractAdapter
import com.td.mylevelup.R
import com.td.mylevelup.components.mortgageCell.MortgageCellBinder

class MortgageCardAdapter(private val context: Context,
                          private val presenter: MortgageCardPresenter): AbstractAdapter() {
    override fun buildRows() {
        add(MortgageCellBinder(context.getString(R.string.mortgage_card_string),
                R.drawable.home, presenter.launchMortgageActivity()))
    }
}