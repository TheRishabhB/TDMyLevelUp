package com.td.mylevelup.mortgage.mortgageResultsPage

interface MortgagePaymentResultView {
    fun getTextView(isEligible: Boolean): String
}