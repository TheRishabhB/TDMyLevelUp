package com.td.mylevelup.components.transactionRow

import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.td.mylevelup.R

class TransactionRowViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    val transactionMerchantName: TextView = itemView.findViewById(R.id.transactionMerchantName)
    val transactionTime: TextView = itemView.findViewById(R.id.transactionTime)
    val transactionAmount: TextView = itemView.findViewById(R.id.transactionAmount)
    companion object {
        @LayoutRes
        fun getLayoutId(): Int {
            return R.layout.transaction_row
        }
    }
}