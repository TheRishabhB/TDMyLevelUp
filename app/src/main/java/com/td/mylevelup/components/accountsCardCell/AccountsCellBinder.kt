package com.td.mylevelup.components.accountsCardCell

import android.support.annotation.DrawableRes
import android.view.ViewGroup
import com.ngam.rvabstractions.binder.AbstractDataBinder
import com.td.virtualbank.VirtualBankBankAccount
import com.td.mylevelup.R

class AccountsCellBinder(private val account: VirtualBankBankAccount,
                         @DrawableRes private val cardImage: Int = R.drawable.debit_card_image): AbstractDataBinder<AccountsCellViewHolder>() {

    override fun createViewHolder(parent: ViewGroup): AccountsCellViewHolder {
        return AccountsCellViewHolder(getView(AccountsCellViewHolder.getLayoutId(), parent))
    }

    override fun bindViewHolder(viewHolder: AccountsCellViewHolder) {
        viewHolder.cardImage.setImageResource(cardImage)
        viewHolder.accountType.text = account.type
        viewHolder.accountBalance.text = String.format("$%.2f", account.balance ?: 0)
    }
}