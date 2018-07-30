package com.td.mylevelup.components.transactionRow

import android.content.Context
import android.graphics.Color
import android.view.ViewGroup
import com.ngam.rvabstractions.binder.AbstractDataBinder
import com.td.mylevelup.R
import com.td.virtualbank.VirtualBankTransaction

class TransactionRowBinder(private val transaction: VirtualBankTransaction):
        AbstractDataBinder<TransactionRowViewHolder>() {

    override fun createViewHolder(parent: ViewGroup): TransactionRowViewHolder {
        return TransactionRowViewHolder(getView(TransactionRowViewHolder.getLayoutId(), parent))
    }

    override fun bindViewHolder(viewHolder: TransactionRowViewHolder) {
        val context: Context = viewHolder.transactionAmount.context

        viewHolder.transactionMerchantName.text = getTransactionName()
        viewHolder.transactionTime.text = transaction.originationDate
        val color: Int = if (transaction.currencyAmount >= 0) context.getColor(R.color.colorPrimaryDark) else Color.RED
        viewHolder.transactionAmount.setTextColor(color)
        viewHolder.transactionAmount.text = String.format("$%.2f", transaction.currencyAmount)
    }

    private fun getTransactionName(): String {
        if (!transaction.merchantName.isNullOrEmpty()) {
            return transaction.merchantName
        } else if (!transaction.description.isNullOrEmpty()) {
            return transaction.description
        } else {
            return "Transaction"
        }
    }
}