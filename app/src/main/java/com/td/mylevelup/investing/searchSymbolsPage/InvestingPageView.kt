package com.td.mylevelup.investing.searchSymbolsPage

interface InvestingPageView {
    fun reloadSymbolsList()
    fun launchSimulationScreenWithSymbol(symbol: String)
}