package com.td.mylevelup.creditCard.cardRecommendation

import com.ngam.rvabstractions.multiCard.AbstractMultiCardPagerAdapter
import com.td.mylevelup.components.creditCardRecommendationCard.CreditCardRecommendationsCardBinder
import com.td.mylevelup.models.CreditCardsDetails

class CreditCardRecommendationAdapter(var recommendationsList: ArrayList<CreditCardsDetails> = ArrayList()):
        AbstractMultiCardPagerAdapter() {
    override fun buildRows() {
        listItems.clear()

        if (recommendationsList.isEmpty()) {
            // If empty list, add all CC
            listItems.add(CreditCardRecommendationsCardBinder(CreditCardsDetails.AEROPLAN_VISA_INFINITE))
            listItems.add(CreditCardRecommendationsCardBinder(CreditCardsDetails.AEROPLAN_VISA_PLATINUM))
            listItems.add(CreditCardRecommendationsCardBinder(CreditCardsDetails.AEROPLAN_VISA_PRIVILEGE))
            listItems.add(CreditCardRecommendationsCardBinder(CreditCardsDetails.CASH_BACK_VISA))
            listItems.add(CreditCardRecommendationsCardBinder(CreditCardsDetails.CASH_BACK_VISA_INFINITE))
            listItems.add(CreditCardRecommendationsCardBinder(CreditCardsDetails.EMERALD_FLEX_RATE))
            listItems.add(CreditCardRecommendationsCardBinder(CreditCardsDetails.FIRST_CLASS_TRAVEL_VISA_INFINITE))
            listItems.add(CreditCardRecommendationsCardBinder(CreditCardsDetails.PLATINUM_TRAVEL))
            listItems.add(CreditCardRecommendationsCardBinder(CreditCardsDetails.REWARDS))
            listItems.add(CreditCardRecommendationsCardBinder(CreditCardsDetails.US_DOLLAR))
            return
        }

        // Add recommendations only
        for (recommendation in recommendationsList) {
            listItems.add(CreditCardRecommendationsCardBinder(recommendation))
        }
    }
}