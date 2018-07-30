package com.td.mylevelup.creditCard.cardPreview

import com.ngam.rvabstractions.adapter.AbstractDataBindAdapter
import com.ngam.rvabstractions.components.shimmer.centerShimmer.CenterShimmerBinder
import com.td.mylevelup.components.creditCardRow.CreditCardRowBinder

class CardPreviewAdapter(private val presenter: CardPreviewPresenter): AbstractDataBindAdapter() {
    override fun buildRows() {
        listItems.clear()

        if (presenter.getAccounts().isEmpty()) {
            add(CenterShimmerBinder())
            add(CenterShimmerBinder())
            return
        }

        for (account in presenter.getAccounts()) {
            add(CreditCardRowBinder(account, inputListener = presenter.getCreditCardChangedClosure()))
        }
    }
}