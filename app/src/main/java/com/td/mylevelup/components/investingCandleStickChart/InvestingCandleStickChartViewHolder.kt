package com.td.mylevelup.components.investingCandleStickChart

import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.view.View
import com.github.mikephil.charting.charts.CandleStickChart
import com.td.mylevelup.R

class InvestingCandleStickChartViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    val chart: CandleStickChart = itemView.findViewById(R.id.summaryCandleStickChart)
    companion object {
        @LayoutRes
        fun getLayoutId(): Int {
            return R.layout.summary_candle_stick_chart
        }
    }
}