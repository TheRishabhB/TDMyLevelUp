package com.td.mylevelup.mortgage.mortgageResultsPage

import com.ngam.rvabstractions.general.AbstractPresenter
import java.math.BigDecimal

class MortgagePaymentResultPresenter(private val view: MortgagePaymentResultView) : AbstractPresenter() {

    fun isCustomerEligible(average: BigDecimal, monthlyPayments: BigDecimal): Boolean {
        return average >= monthlyPayments
    }
}