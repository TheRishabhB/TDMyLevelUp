package com.td.mylevelup.accounts.bottomView

import com.ngam.rvabstractions.adapter.AbstractDataBindAdapter
import com.ngam.rvabstractions.components.shimmer.sideShimmer.ShimmerBinder
import com.ngam.rvabstractions.components.title.sideTitle.SideTitleBinder
import com.td.mylevelup.components.accountsRecommendationRow.AccountsRecommendationRowBinder
import com.td.mylevelup.components.pieChart.TransactionsPieChartBinder
import com.td.mylevelup.components.transactionRow.TransactionRowBinder

class AccountsBottomAdapter(private val presenter: AccountsBottomPresenter): AbstractDataBindAdapter() {
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
        add(SideTitleBinder("Transactions: ", 30f))
        if (presenter.shouldShowShimmer) {
            add(ShimmerBinder())
            add(ShimmerBinder())
            add(ShimmerBinder())
            add(ShimmerBinder())
            return
        }
        add(SideTitleBinder(presenter.getFormattedAccountString(), 20f))
        for (transaction in presenter.transactions) {
            add(TransactionRowBinder(transaction))
        }
    }
}