package com.td.mylevelup.components.creditCardRow

import android.support.annotation.DrawableRes
import android.view.ViewGroup
import com.ngam.rvabstractions.general.AbstractDataBinder
import com.ngam.rvabstractions.general.OnInputReceived
import com.ngam.rvabstractions.general.SimpleOnInputListener
import com.td.virtualbank.VirtualBankCreditCardAccount
import com.td.mylevelup.R

class CreditCardRowBinder(
        private val account: VirtualBankCreditCardAccount,
        @DrawableRes private val logo: Int = R.drawable.td_logo,
        private val inputListener: OnInputReceived<VirtualBankCreditCardAccount> = SimpleOnInputListener()):
        AbstractDataBinder<CreditCardRowViewHolder>() {
    override fun createViewHolder(parent: ViewGroup): CreditCardRowViewHolder {
        return CreditCardRowViewHolder(getView(CreditCardRowViewHolder.getLayoutId(), parent))
    }

    override fun bindViewHolder(viewHolder: CreditCardRowViewHolder) {
        viewHolder.cardIcon.setImageResource(logo)
        viewHolder.cardBalance.text = String.format("$%.2f", account.balance ?: 0)
        viewHolder.cardNumber.text = String.format("VISA - %s", account.maskedNumber)

        viewHolder.cardInfoParent.setOnClickListener {
            inputListener.onInputReceived(account)
        }
    }
}