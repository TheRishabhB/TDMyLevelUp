package com.td.mylevelup.components.pieChart

import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.view.View
import com.github.mikephil.charting.charts.PieChart
import com.td.mylevelup.R

class TransactionsPieChartViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    val chart: PieChart = itemView.findViewById(R.id.pieChartTransactions)
    companion object {
        @LayoutRes
        fun getLayoutId(): Int {
            return R.layout.summary_pie_chart
        }
    }
}