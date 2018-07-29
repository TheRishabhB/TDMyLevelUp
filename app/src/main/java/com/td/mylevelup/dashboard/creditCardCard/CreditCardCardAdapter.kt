package com.td.mylevelup.dashboard.creditCardCard

import com.ngam.rvabstractions.adapter.AbstractDataBindAdapter
import com.ngam.rvabstractions.components.shimmer.ShimmerBinder
import com.td.mylevelup.components.creditCardRow.CreditCardRowBinder
import com.td.mylevelup.components.creditCardShimmer.CreditCardShimmerBinder

class CreditCardCardAdapter(private val presenter: CreditCardCardPresenter): AbstractDataBindAdapter() {
    override fun buildRows() {
        listItems.clear()

        if (presenter.getAccounts().isEmpty()) {
            //add(CreditCardShimmerBinder())
            add(ShimmerBinder())
            add(ShimmerBinder())
            return
        }

        for (account in presenter.getAccounts()) {
            add(CreditCardRowBinder(account))
        }
    }
}