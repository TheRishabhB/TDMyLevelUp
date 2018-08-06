package com.td.mylevelup.components.simulationResults

import android.view.ViewGroup
import com.ngam.rvabstractions.general.AbstractDataBinder
import com.td.mylevelup.models.InvestingSimulationTransaction

class SimulationResultsRowBinder(private val transaction: InvestingSimulationTransaction,
                                 private val symbol: String):
        AbstractDataBinder<SimulationResultsRowViewHolder>() {
    override fun createViewHolder(parent: ViewGroup): SimulationResultsRowViewHolder {
        return SimulationResultsRowViewHolder(getView(SimulationResultsRowViewHolder.getLayoutId(), parent))
    }

    override fun bindViewHolder(viewHolder: SimulationResultsRowViewHolder) {
        viewHolder.tradeName.text = String.format("%1s - [%2s]", symbol, transaction.expenseName)
        viewHolder.tradeDetails.text = String.format("BUY - %.8f @ $%.2f", transaction.amount, transaction.price)
        viewHolder.dateDetails.text = transaction.date
    }
}