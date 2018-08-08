package com.td.mylevelup.components.investingCandleStickChart

import android.graphics.Color
import android.graphics.Paint
import android.view.ViewGroup
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.data.CandleData
import com.github.mikephil.charting.data.CandleDataSet
import com.github.mikephil.charting.data.CandleEntry
import com.ngam.rvabstractions.general.AbstractDataBinder
import com.td.mylevelup.models.alphaVantage.SymbolDayInformation
import java.util.*
import kotlin.collections.HashMap

class InvestingCandleStickChartBinder(private val data: HashMap<String, SymbolDayInformation>,
                                      private val symbolName: String): AbstractDataBinder<InvestingCandleStickChartViewHolder>() {
    override fun createViewHolder(parent: ViewGroup): InvestingCandleStickChartViewHolder {
        return InvestingCandleStickChartViewHolder(getView(InvestingCandleStickChartViewHolder.getLayoutId(), parent))
    }

    override fun bindViewHolder(viewHolder: InvestingCandleStickChartViewHolder) {
        if (data.isEmpty()) return


        val candleEntries: ArrayList<CandleEntry> = ArrayList()
        var index = 0
        for (entry in prepareData()) {
            candleEntries.add(CandleEntry(index.toFloat(), entry.value.high.toFloat(), entry.value.low.toFloat(),
                    entry.value.open.toFloat(), entry.value.close.toFloat()))
            index++
        }

        val dataSet = CandleDataSet(candleEntries, "Data for $symbolName")
        dataSet.color = Color.rgb(80, 80, 80)
        dataSet.shadowColor = Color.DKGRAY
        dataSet.shadowWidth = 0.7f
        dataSet.decreasingColor = Color.RED
        dataSet.decreasingPaintStyle = Paint.Style.FILL
        dataSet.increasingColor = Color.rgb(122, 242, 84)
        dataSet.decreasingPaintStyle = Paint.Style.FILL
        dataSet.neutralColor = Color.BLUE

        viewHolder.chart.data = CandleData(dataSet)
        viewHolder.chart.animateX(1000)
    }

    private fun prepareData(): Map<String, SymbolDayInformation> {
        val sortedDates: List<String> = data.keys.sorted()
        val dates: List<String> = sortedDates.subList(sortedDates.size - 31, sortedDates.size - 1)
        val last30DayPriceMap: HashMap<String, SymbolDayInformation> = HashMap()
        for (date in dates) {
            last30DayPriceMap[date] = data[date] ?: continue
        }
        return last30DayPriceMap.toList().sortedBy { (key, _) -> key }.toMap()
    }
}