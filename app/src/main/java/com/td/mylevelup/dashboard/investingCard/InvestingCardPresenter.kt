package com.td.mylevelup.dashboard.investingCard

import android.view.View
import com.ngam.rvabstractions.general.AbstractPresenter

class InvestingCardPresenter(private val view: InvestingCardView): AbstractPresenter() {
    fun createLaunchInvestingSearchClickListener() : View.OnClickListener {
        return View.OnClickListener {
            view.launchSearchStocksScreen()
        }
    }
}