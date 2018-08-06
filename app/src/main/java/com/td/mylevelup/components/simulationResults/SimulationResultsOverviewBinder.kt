package com.td.mylevelup.components.simulationResults

import android.content.Context
import android.support.v4.content.ContextCompat
import android.view.ViewGroup
import com.ngam.rvabstractions.general.AbstractDataBinder
import com.td.mylevelup.R

class SimulationResultsOverviewBinder(private val titleText: String,
                                      private val amountText: String,
                                      private val shouldColorAmountRed: Boolean = false):
        AbstractDataBinder<SimulationResultsOverviewViewHolder>() {
    override fun createViewHolder(parent: ViewGroup): SimulationResultsOverviewViewHolder {
        return SimulationResultsOverviewViewHolder(getView(SimulationResultsOverviewViewHolder.getLayoutId(), parent))
    }

    override fun bindViewHolder(viewHolder: SimulationResultsOverviewViewHolder) {
        val context: Context = viewHolder.resultsAmount.context
        viewHolder.resultsTitle.text = titleText
        viewHolder.resultsAmount.text = amountText

        viewHolder.resultsAmount.setTextColor(ContextCompat.getColor(context,
                if (shouldColorAmountRed) R.color.red else R.color.colorPrimary))
    }
}