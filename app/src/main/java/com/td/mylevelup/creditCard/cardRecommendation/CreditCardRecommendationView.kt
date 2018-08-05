package com.td.mylevelup.creditCard.cardRecommendation

import android.content.Context
import android.util.AttributeSet
import com.ngam.rvabstractions.multiCard.AbstractMultiCardPagerView
import com.ngam.rvabstractions.multiCard.MultiCardDataSource
import com.td.mylevelup.models.CreditCardsDetails

class CreditCardRecommendationView(context: Context, attrSet: AttributeSet?, defStyleAttr: Int):
        AbstractMultiCardPagerView<CreditCardRecommendationAdapter>(context, attrSet, defStyleAttr) {
    // Constructors for LinearLayout
    constructor(context: Context, attrSet: AttributeSet?) : this(context, attrSet, 0)
    constructor(context: Context) : this(context, null)

    override fun setDataSource(): MultiCardDataSource<CreditCardRecommendationAdapter> {
        adapter = CreditCardRecommendationAdapter()
        return MultiCardDataSource(adapter, pagerHeight = 175,
                pagerLeftPadding = 50, pagerRightPadding = 150)
    }

    fun setRecommendations(list: ArrayList<CreditCardsDetails>) {
        adapter.recommendationsList = list
        adapter.buildRows()
        adapter.notifyDataSetChanged()
    }
}