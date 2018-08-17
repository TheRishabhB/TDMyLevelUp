package com.td.mylevelup.dashboard.mortgageCard

import android.view.View
import com.ngam.rvabstractions.general.AbstractPresenter

class MortgageCardPresenter(private val view: MortgageCardView): AbstractPresenter() {
    fun launchMortgageActivity() : View.OnClickListener {
        return View.OnClickListener {
            view.launchMortgageActivity()
        }
    }

}