package com.td.mylevelup.components.searchSymbolRow

import android.view.ViewGroup
import com.ngam.rvabstractions.general.AbstractDataBinder

class SymbolSearchInfoBinder(private val shortName: String,
                             private val longName: String): AbstractDataBinder<SymbolSearchInfoViewHolder>() {
    override fun createViewHolder(parent: ViewGroup): SymbolSearchInfoViewHolder {
        return SymbolSearchInfoViewHolder(getView(SymbolSearchInfoViewHolder.getLayoutId(), parent))
    }

    override fun bindViewHolder(viewHolder: SymbolSearchInfoViewHolder) {
        viewHolder.shortName.text = shortName
        viewHolder.longName.text = longName
    }
}