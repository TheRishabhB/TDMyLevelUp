package com.td.mylevelup.components.simulationResults

import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.td.mylevelup.R

class SimulationResultsOverviewViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    val resultsTitle: TextView = itemView.findViewById(R.id.resultsTitle)
    val resultsAmount: TextView = itemView.findViewById(R.id.resultsAmount)
    companion object {
        @LayoutRes
        fun getLayoutId(): Int {
            return R.layout.investing_search_simulation_results_summary
        }
    }
}