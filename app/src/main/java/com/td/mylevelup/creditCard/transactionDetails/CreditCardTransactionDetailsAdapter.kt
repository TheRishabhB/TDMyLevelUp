package com.td.mylevelup.creditCard.transactionDetails

import com.ngam.rvabstractions.components.shimmer.ShimmerBinder
import com.ngam.rvabstractions.components.title.centerTitle.CenterTitleBinder
import com.ngam.rvabstractions.components.title.sideTitle.SideTitleBinder
import com.ngam.rvabstractions.general.AbstractAdapter
import com.td.mylevelup.components.pieChart.TransactionsPieChartBinder
import com.td.mylevelup.components.transactionRow.TransactionRowBinder
import com.td.mylevelup.creditCard.cardRecommendation.CreditCardRecommendationBinder

class CreditCardTransactionDetailsAdapter(
        private val presenter: CreditCardTransactionDetailsPresenter): AbstractAdapter() {
    override fun buildRows() {
        listItems.clear()

        add(SideTitleBinder("Recommendations: ", 30f))
        add(CreditCardRecommendationBinder(presenter.getCreditCardRecommendations()))

        // Return if error state
        if (presenter.errorState){
            add(SideTitleBinder("Transactions: ", 30f))
            add(CenterTitleBinder("Unable to get transactions", 30f))
            return
        }

        add(SideTitleBinder("Summary: ", 30f))
        add(TransactionsPieChartBinder(presenter.getTransactions()))

        // Transactions
        add(SideTitleBinder(String.format("Transactions (%d): ", presenter.getTransactions().size), 30f))
        if (presenter.getTransactions().isEmpty()) {
            add(ShimmerBinder())
            add(ShimmerBinder())
            add(ShimmerBinder())
            add(ShimmerBinder())
            return
        }

        add(SideTitleBinder(presenter.getFormattedAccountString(), 20f))
        for (transaction in presenter.getTransactions()) {
            add(TransactionRowBinder(transaction))
        }
    }
}