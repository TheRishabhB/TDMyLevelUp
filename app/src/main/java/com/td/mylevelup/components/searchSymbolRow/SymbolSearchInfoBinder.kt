package com.td.mylevelup.components.searchSymbolRow

import android.view.ViewGroup
import com.ngam.rvabstractions.general.AbstractDataBinder
import com.ngam.rvabstractions.general.OnInputReceived
import com.ngam.rvabstractions.general.SimpleOnInputListener

class SymbolSearchInfoBinder(private val shortName: String,
                             private val longName: String,
                             private val inputListener: OnInputReceived<String> = SimpleOnInputListener()): AbstractDataBinder<SymbolSearchInfoViewHolder>() {
    override fun createViewHolder(parent: ViewGroup): SymbolSearchInfoViewHolder {
        return SymbolSearchInfoViewHolder(getView(SymbolSearchInfoViewHolder.getLayoutId(), parent))
    }

    override fun bindViewHolder(viewHolder: SymbolSearchInfoViewHolder) {
        viewHolder.shortName.text = shortName
        viewHolder.longName.text = longName
        viewHolder.relativeLayout.setOnClickListener {
            inputListener.onInputReceived(shortName)
        }
    }
}