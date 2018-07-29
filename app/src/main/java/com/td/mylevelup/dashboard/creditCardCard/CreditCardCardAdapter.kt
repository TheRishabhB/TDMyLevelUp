package com.td.mylevelup.dashboard.creditCardCard

import com.ngam.rvabstractions.adapter.AbstractDataBindAdapter
import com.ngam.rvabstractions.components.shimmer.centerShimmer.CenterShimmerBinder
import com.td.mylevelup.components.creditCardRow.CreditCardRowBinder

class CreditCardCardAdapter(private val presenter: CreditCardCardPresenter): AbstractDataBindAdapter() {
    override fun buildRows() {
        listItems.clear()

        if (presenter.getAccounts().isEmpty()) {
            //add(CreditCardShimmerBinder())
            add(CenterShimmerBinder())
            add(CenterShimmerBinder())
            return
        }

        for (account in presenter.getAccounts()) {
            add(CreditCardRowBinder(account))
        }
    }
}