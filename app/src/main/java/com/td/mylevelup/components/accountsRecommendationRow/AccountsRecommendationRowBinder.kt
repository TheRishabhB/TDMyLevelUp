package com.td.mylevelup.components.accountsRecommendationRow

import android.content.Context
import android.net.Uri
import android.support.customtabs.CustomTabsIntent
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.ViewGroup
import android.widget.TextView
import com.ngam.rvabstractions.binder.AbstractDataBinder
import com.td.mylevelup.R
import com.td.mylevelup.models.PersonalAccountsEnum

class AccountsRecommendationRowBinder(private val accountRecommendation: PersonalAccountsEnum):
        AbstractDataBinder<AccountsRecommendationRowViewHolder>() {
    override fun createViewHolder(parent: ViewGroup): AccountsRecommendationRowViewHolder {
        return AccountsRecommendationRowViewHolder(getView(AccountsRecommendationRowViewHolder.getLayoutId(), parent))
    }

    override fun bindViewHolder(viewHolder: AccountsRecommendationRowViewHolder) {
        val context: Context = viewHolder.recommendationsText.context
        // Get recommendation text and set colors.
        val reasonString: String = accountRecommendation.getRecommendedText()
        val accountName: String = accountRecommendation.getAccountName()
        val recommendationString: String = reasonString + accountName
        val spannable: Spannable = SpannableString(recommendationString)
        spannable.setSpan(ForegroundColorSpan(context.getColor(R.color.colorPrimaryDark)),
                reasonString.length, recommendationString.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        viewHolder.recommendationsText.setText(spannable, TextView.BufferType.SPANNABLE)

        // Set CustomTab when button is clicked.
        viewHolder.learnMoreButton.setOnClickListener {
            val intent: CustomTabsIntent = CustomTabsIntent.Builder().build()
            intent.launchUrl(context, Uri.parse(accountRecommendation.getAccountURL()))
        }

    }

}