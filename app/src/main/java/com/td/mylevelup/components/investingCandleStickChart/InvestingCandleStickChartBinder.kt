package com.td.mylevelup.components.investingCandleStickChart

import android.view.ViewGroup
import com.github.mikephil.charting.animation.Easing
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
//        for (datum in data) {
//            candleEntries.add(CandleEntry(, entry.value.high.toFloat(), entry.value.low.toFloat(),
//                    entry.value.open.toFloat(), entry.value.close.toFloat()))
//        }
        for (data in prepareData()) {
            val x = 5
        }

        viewHolder.chart.animateX(1000, Easing.EasingOption.Linear)
        viewHolder.chart.animateY(1000, Easing.EasingOption.Linear)
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