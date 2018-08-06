package com.td.mylevelup.components.searchSymbolRow

import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.td.mylevelup.R

class SymbolSearchInfoViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    val shortName: TextView = itemView.findViewById(R.id.symbolShortName)
    val longName: TextView = itemView.findViewById(R.id.symbolFullName)
    companion object {
        @LayoutRes
        fun getLayoutId(): Int {
            return R.layout.investing_search_symbol_row
        }
    }
}