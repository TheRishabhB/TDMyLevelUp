package com.td.mylevelup.creditCard.cardRecommendation

import android.view.ViewGroup
import com.ngam.rvabstractions.general.AbstractDataBinder
import com.td.mylevelup.models.CreditCardsDetails

class CreditCardRecommendationBinder(private val recommendationsList: ArrayList<CreditCardsDetails> = ArrayList()):
        AbstractDataBinder<CreditCardRecommendationViewHolder>() {
    override fun createViewHolder(parent: ViewGroup): CreditCardRecommendationViewHolder {
        return CreditCardRecommendationViewHolder(getView(CreditCardRecommendationViewHolder.getLayoutId(), parent))
    }

    override fun bindViewHolder(viewHolder: CreditCardRecommendationViewHolder) {
        viewHolder.creditCardRecommendations.setRecommendations(recommendationsList)
    }
}