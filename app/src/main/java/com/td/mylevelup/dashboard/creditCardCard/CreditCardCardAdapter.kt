package com.td.mylevelup.dashboard.creditCardCard

import com.ngam.rvabstractions.components.title.centerTitle.CenterTitleBinder
import com.ngam.rvabstractions.general.AbstractAdapter
import com.td.mylevelup.components.creditCardRow.CreditCardRowBinder
import com.td.mylevelup.components.creditCardShimmer.CreditCardShimmerBinder

class CreditCardCardAdapter(private val presenter: CreditCardCardPresenter): AbstractAdapter() {
    override fun buildRows() {
        listItems.clear()

        if (presenter.isError) {
            add(CenterTitleBinder("It's Us Not You (╯°□°）╯︵ ┻━┻", 20f))
            return
        }

        if (presenter.getAccounts().isEmpty()) {
            add(CreditCardShimmerBinder())
            return
        }

        for (account in presenter.getAccounts()) {
            add(CreditCardRowBinder(account, inputListener = presenter.createCardInputListener()))
        }
    }
}