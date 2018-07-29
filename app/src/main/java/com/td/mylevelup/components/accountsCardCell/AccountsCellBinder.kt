package com.td.mylevelup.components.accountsCardCell

import android.support.annotation.DrawableRes
import android.view.View
import android.view.ViewGroup
import com.ngam.rvabstractions.binder.AbstractDataBinder
import com.ngam.rvabstractions.contracts.OnInputReceived
import com.ngam.rvabstractions.contracts.SimpleOnInputListener
import com.td.virtualbank.VirtualBankBankAccount
import com.td.mylevelup.R

class AccountsCellBinder(private val account: VirtualBankBankAccount,
                         @DrawableRes private val cardImage: Int = R.drawable.debit_card_image,
                         private val inputListener: OnInputReceived<VirtualBankBankAccount> = SimpleOnInputListener()):
        AbstractDataBinder<AccountsCellViewHolder>() {

    override fun createViewHolder(parent: ViewGroup): AccountsCellViewHolder {
        return AccountsCellViewHolder(getView(AccountsCellViewHolder.getLayoutId(), parent))
    }

    override fun bindViewHolder(viewHolder: AccountsCellViewHolder) {
        viewHolder.cardImage.setImageResource(cardImage)
        viewHolder.accountType.text = account.type
        viewHolder.accountBalance.text = String.format("$%.2f", account.balance ?: 0)

        viewHolder.parent.setOnClickListener {
            inputListener.onInputReceived(account)
        }
    }
}