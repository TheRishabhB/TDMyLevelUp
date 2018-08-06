package com.td.mylevelup.components.pieChart

import android.graphics.Color
import android.view.ViewGroup
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.IValueFormatter
import com.github.mikephil.charting.utils.ColorTemplate
import com.github.mikephil.charting.utils.ViewPortHandler
import com.ngam.rvabstractions.general.AbstractDataBinder
import com.td.virtualbank.VirtualBankTransaction
import java.util.ArrayList
import kotlin.math.absoluteValue

class TransactionsPieChartBinder(private val transactions: ArrayList<VirtualBankTransaction>):
        AbstractDataBinder<TransactionsPieChartViewHolder>() {

    override fun createViewHolder(parent: ViewGroup): TransactionsPieChartViewHolder {
        return TransactionsPieChartViewHolder(getView(TransactionsPieChartViewHolder.getLayoutId(), parent))
    }

    override fun bindViewHolder(viewHolder: TransactionsPieChartViewHolder) {
        if (transactions.isEmpty()) {
            return
        }
        customizePieChartLook(viewHolder.chart)
        viewHolder.chart.data = parseTransactionDataToPieData()
        viewHolder.chart.animateY(1000, Easing.EasingOption.Linear)
    }

    private fun parseTransactionDataToPieData(): PieData {
        // Create a map of vendor : money spent at vendor
        val vendorToCostMap: HashMap<String, Double> = HashMap()
        // Create a map of Vendor : All Transactions for Vendor
        val mappedMerchantNamedTransactions: Map<String, List<VirtualBankTransaction>> = transactions.groupBy { getTransactionName(it) }
        // Populate vendorToCostMap
        for (mappedValue in mappedMerchantNamedTransactions) {
            val numberSpentAtVendor: Double = mappedValue.value.sumByDouble { it.currencyAmount.absoluteValue }
            vendorToCostMap[mappedValue.key] = numberSpentAtVendor
        }

        val amountSpent: ArrayList<PieEntry> = ArrayList()
        for (mappedValue in vendorToCostMap) {
            if (amountSpent.size >= 4) {
                break
            }
            amountSpent.add(PieEntry(mappedValue.value.toFloat(), mappedValue.key))
        }
        val pieDataSet = PieDataSet(amountSpent, "Amount Spent at Each Vendor")
        setPieDataSetCustomizations(pieDataSet)

        return PieData(pieDataSet)
    }

    private fun setPieDataSetCustomizations(dataSet: PieDataSet) {
        dataSet.xValuePosition = PieDataSet.ValuePosition.OUTSIDE_SLICE
        dataSet.yValuePosition = PieDataSet.ValuePosition.OUTSIDE_SLICE
        dataSet.valueFormatter = customValueFormatter()
        dataSet.valueTextSize = 15f

        val colors: ArrayList<kotlin.Int> = ArrayList()
        for (color in ColorTemplate.COLORFUL_COLORS) {
            colors.add(color)
        }

        dataSet.colors = colors
    }

    private fun customValueFormatter(): IValueFormatter {
        return object: IValueFormatter {
            override fun getFormattedValue(value: Float, entry: Entry?, dataSetIndex: Int, viewPortHandler: ViewPortHandler?): String {
                return String.format("$%.2f", value)
            }
        }
    }

    private fun customizePieChartLook(chart: PieChart) {
        chart.description.isEnabled = false
        chart.setEntryLabelColor(Color.BLACK)
        chart.setEntryLabelTextSize(10f)
        chart.isDrawHoleEnabled = true
        chart.holeRadius = 40f
    }

    private fun getTransactionName(transaction: VirtualBankTransaction): String {
        if (!transaction.merchantName.isNullOrEmpty()) {
            return transaction.merchantName
        } else if (!transaction.description.isNullOrEmpty()) {
            return transaction.description
        } else {
            return "Transaction"
        }
    }
}