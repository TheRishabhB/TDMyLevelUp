package com.td.mylevelup.dashboard.accountsCard

import com.ngam.rvabstractions.adapter.AbstractDataBindAdapter
import com.ngam.rvabstractions.components.smallShimmer.SmallShimmerBinder
import com.td.mylevelup.components.accountsCardCell.AccountsCellBinder


class AccountsCardAdapter(private val presenter: AccountsCardPresenter): AbstractDataBindAdapter() {
    override fun buildRows() {
        listItems.clear()
        // Add shimmer if no accounts to show
        if (presenter.getAccounts().isEmpty()) {
            add(SmallShimmerBinder())
            add(SmallShimmerBinder())
            return
        }

        for (account in presenter.getAccounts()) {
            add(AccountsCellBinder(account))
        }
    }
}