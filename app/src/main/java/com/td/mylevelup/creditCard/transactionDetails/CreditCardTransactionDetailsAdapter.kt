package com.td.mylevelup.creditCard.transactionDetails

import com.ngam.rvabstractions.adapter.AbstractDataBindAdapter
import com.ngam.rvabstractions.components.shimmer.sideShimmer.ShimmerBinder
import com.ngam.rvabstractions.components.title.sideTitle.SideTitleBinder
import com.td.mylevelup.components.pieChart.TransactionsPieChartBinder
import com.td.mylevelup.components.transactionRow.TransactionRowBinder

class CreditCardTransactionDetailsAdapter(
        private val presenter: CreditCardTransactionDetailsPresenter): AbstractDataBindAdapter() {
    override fun buildRows() {
        listItems.clear()
        add(SideTitleBinder("Summary: ", 30f))
        add(TransactionsPieChartBinder(presenter.getTransactions()))


        // Transactions
        add(SideTitleBinder("Transactions: ", 30f))
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