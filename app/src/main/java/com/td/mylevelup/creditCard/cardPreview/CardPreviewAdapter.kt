package com.td.mylevelup.creditCard.cardPreview

import com.ngam.rvabstractions.general.AbstractAdapter
import com.td.mylevelup.components.creditCardRow.CreditCardRowBinder
import com.td.mylevelup.components.creditCardShimmer.CreditCardShimmerBinder

class CardPreviewAdapter(private val presenter: CardPreviewPresenter): AbstractAdapter() {
    override fun buildRows() {
        listItems.clear()

        if (presenter.getAccounts().isEmpty()) {
            add(CreditCardShimmerBinder())
            return
        }

        for (account in presenter.getAccounts()) {
            add(CreditCardRowBinder(account, inputListener = presenter.getCreditCardChangedClosure()))
        }
    }
}