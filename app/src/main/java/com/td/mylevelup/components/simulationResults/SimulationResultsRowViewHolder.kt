package com.td.mylevelup.components.simulationResults

import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.td.mylevelup.R

class SimulationResultsRowViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    val tradeName: TextView = itemView.findViewById(R.id.symbolTradeNameDetails)
    val tradeDetails: TextView = itemView.findViewById(R.id.symbolTradeAmountDetails)
    val dateDetails: TextView = itemView.findViewById(R.id.symbolTradeDateDetails)
    companion object {
        @LayoutRes
        fun getLayoutId(): Int {
            return R.layout.investing_simulation_results_trade_row
        }
    }
}