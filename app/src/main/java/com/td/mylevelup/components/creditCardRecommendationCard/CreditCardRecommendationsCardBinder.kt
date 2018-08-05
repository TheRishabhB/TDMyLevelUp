package com.td.mylevelup.components.creditCardRecommendationCard

import android.net.Uri
import android.support.customtabs.CustomTabsIntent
import android.support.v7.widget.CardView
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.ngam.rvabstractions.multiCard.AbstractMultiCardBinder
import com.td.mylevelup.R
import com.td.mylevelup.models.CreditCardsDetails

class CreditCardRecommendationsCardBinder(private val creditCardDetails: CreditCardsDetails):
        AbstractMultiCardBinder() {
    override fun createItem(container: ViewGroup): View {
        return getView(R.layout.credit_card_recommendations, container)
    }

    override fun getCardView(view: View): CardView {
        return view.findViewById(R.id.recommendationsCardView)
    }

    override fun bindData(view: View) {
        val cardImage: ImageView = view.findViewById(R.id.cardImage)
        val cardText: TextView = view.findViewById(R.id.cardRecommendationDescription)
        val learnButton: Button = view.findViewById(R.id.learnMoreButton)

        cardImage.setImageResource(creditCardDetails.getCardImage())
        cardText.text = String.format("After analyzing your profile, we recommend the %s",
                creditCardDetails.getCardName())
        learnButton.text = "Learn More"

        val cardLayout: CardView = view.findViewById(R.id.recommendationsCardView)

        cardLayout.setOnClickListener {
            val intent: CustomTabsIntent = CustomTabsIntent.Builder().build()
            intent.launchUrl(view.context, Uri.parse(creditCardDetails.getUrl()))
        }
    }
}