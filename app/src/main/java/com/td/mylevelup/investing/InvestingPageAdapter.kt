package com.td.mylevelup.investing

import com.ngam.rvabstractions.components.title.centerTitle.CenterTitleBinder
import com.ngam.rvabstractions.components.title.sideTitle.SideTitleBinder
import com.ngam.rvabstractions.general.AbstractAdapter
import com.td.mylevelup.components.searchSymbolRow.SymbolSearchInfoBinder

class InvestingPageAdapter(private val presenter: InvestingPagePresenter): AbstractAdapter() {
    override fun buildRows() {
        listItems.clear()

        // Nothing in search bar
        if (presenter.getUpdatedSearchQuery().isNullOrEmpty()) {
            add(SideTitleBinder("Suggestions: ", 30f))
            add(SideTitleBinder("", 15f))
            add((SymbolSearchInfoBinder("MSFT", "Microsoft Corporation")))
            add((SymbolSearchInfoBinder("NFLX", "Netflix")))
            add((SymbolSearchInfoBinder("FB", "Facebook")))
            return
        }

        if (presenter.getUpdatedSymbolsList().isEmpty()) {
            add(CenterTitleBinder("", 15f))
            add(CenterTitleBinder(String.format("We have no matches for %s", presenter.getUpdatedSearchQuery()), 30f))
            return
        }

        add(SideTitleBinder(String.format("Symbols (%d): ", presenter.getUpdatedSymbolsList().size), 30f))
        add(SideTitleBinder("", 15f))
        for (symbol in presenter.getUpdatedSymbolsList()) {
            add(SymbolSearchInfoBinder(symbol.symbol, symbol.name))
        }
    }
}