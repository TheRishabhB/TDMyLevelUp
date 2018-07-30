package com.td.mylevelup.components.creditCardRow

import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.td.mylevelup.R

class CreditCardRowViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    val cardInfoParent: RelativeLayout = itemView.findViewById(R.id.cardInfoParent)
    val cardIcon: ImageView = itemView.findViewById(R.id.creditCardLogo)
    val cardBalance: TextView = itemView.findViewById(R.id.creditCardBalance)
    val cardNumber: TextView = itemView.findViewById(R.id.creditCardNumber)

    companion object {
        @LayoutRes
        fun getLayoutId(): Int {
            return R.layout.credit_card_info
        }
    }
}