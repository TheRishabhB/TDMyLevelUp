package com.td.mylevelup.dashboard.accountsCard

import com.ngam.rvabstractions.components.smallShimmer.SmallShimmerBinder
import com.ngam.rvabstractions.components.title.centerTitle.CenterTitleBinder
import com.ngam.rvabstractions.general.AbstractAdapter
import com.td.mylevelup.components.accountsCardCell.AccountsCellBinder


class AccountsCardAdapter(private val presenter: AccountsCardPresenter): AbstractAdapter() {
    override fun buildRows() {
        listItems.clear()

        if (presenter.isError) {
            add(CenterTitleBinder("It's Us Not You (╯°□°）╯︵ ┻━┻", 20f))
            return
        }

        // Add shimmer if no accounts to show
        if (presenter.getAccounts().isEmpty()) {
            add(SmallShimmerBinder())
            add(SmallShimmerBinder())
            return
        }

        for (account in presenter.getAccounts()) {
            add(AccountsCellBinder(account, inputListener = presenter.createAccountInputListener()))
        }
    }
}