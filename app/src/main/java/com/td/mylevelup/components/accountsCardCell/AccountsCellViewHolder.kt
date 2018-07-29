package com.td.mylevelup.components.accountsCardCell

import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.td.mylevelup.R

class AccountsCellViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    val cardImage: ImageView = itemView.findViewById(R.id.debitCardImage)
    val accountType: TextView = itemView.findViewById(R.id.debitAccountType)
    val accountBalance: TextView = itemView.findViewById(R.id.shimmerCellBalance)

    companion object {
        @LayoutRes
        fun getLayoutId(): Int {
            return R.layout.small_card_row
        }
    }
}