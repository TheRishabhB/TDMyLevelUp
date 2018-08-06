package com.td.mylevelup.investing.simulationResultsPage

import com.ngam.rvabstractions.components.title.sideTitle.SideTitleBinder
import com.ngam.rvabstractions.general.AbstractAdapter
import com.td.mylevelup.components.creditCardShimmer.CreditCardShimmerBinder
import com.td.mylevelup.components.simulationResults.SimulationResultsOverviewBinder
import com.td.mylevelup.components.simulationResults.SimulationResultsRowBinder

class InvestingSimulationAdapter(private val presenter: InvestingSimulationPresenter): AbstractAdapter() {
    override fun buildRows() {
        listItems.clear()

        if (presenter.getSymbolTrades().isEmpty()) {
            add(CreditCardShimmerBinder())
            return
        }

        add(SideTitleBinder("", 10f))
        add(SideTitleBinder(String.format("Results (%s)", presenter.getSymbol()), 30f))
        add(SideTitleBinder("", 15f))

        add(SimulationResultsOverviewBinder("Total Expenses: ", String.format("$%.2f", presenter.getTotalExpenses()), true))
        add(SimulationResultsOverviewBinder("Value after Investing: ",
                String.format("$%.2f", presenter.getTotalExpenses() + presenter.getTotalGrowth())))
        add(SimulationResultsOverviewBinder("Total Growth: ", String.format("$%.2f", presenter.getTotalGrowth())))

        // TODO: Candle Stick Chart

        add(SideTitleBinder("", 10f))
        add(SideTitleBinder(String.format("Trades to be Made (%d)", presenter.getSymbolTrades().size), 30f))
        add(SideTitleBinder("", 10f))
        for (trade in presenter.getSymbolTrades()) {
            add(SimulationResultsRowBinder(trade, presenter.getSymbol()))
        }
    }
}