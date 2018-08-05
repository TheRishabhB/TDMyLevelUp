package com.td.mylevelup.accounts.bottomView

import com.ngam.rvabstractions.components.shimmer.ShimmerBinder
import com.ngam.rvabstractions.components.title.sideTitle.SideTitleBinder
import com.ngam.rvabstractions.general.AbstractAdapter
import com.td.mylevelup.components.accountsRecommendationRow.AccountsRecommendationRowBinder
import com.td.mylevelup.components.pieChart.TransactionsPieChartBinder
import com.td.mylevelup.components.transactionRow.TransactionRowBinder

class AccountsBottomAdapter(private val presenter: AccountsBottomPresenter): AbstractAdapter() {
    override fun buildRows() {
        listItems.clear()

        // Recommendations
        add(SideTitleBinder("Recommendations: ", 30f))
        if (presenter.accounts.isEmpty()) {
            add(ShimmerBinder())
        } else {
            add(AccountsRecommendationRowBinder(presenter.getAccountRecommendations()))
        }

        add(SideTitleBinder("Summary: ", 30f))
        add(TransactionsPieChartBinder(if (presenter.shouldShowShimmer) ArrayList() else presenter.transactions))

        // Transactions
        if (presenter.shouldShowShimmer) {
            add(SideTitleBinder("Transactions: ", 30f))
            add(ShimmerBinder())
            add(ShimmerBinder())
            add(ShimmerBinder())
            add(ShimmerBinder())
            return
        }
        add(SideTitleBinder(String.format("Transactions (%d): ", presenter.transactions.size), 30f))
        add(SideTitleBinder(presenter.getFormattedAccountString(), 20f))
        for (transaction in presenter.transactions) {
            add(TransactionRowBinder(transaction))
        }
    }
}