package com.td.mylevelup.investing.simulationResultsPage

import com.google.gson.Gson
import com.ngam.rvabstractions.general.AbstractPresenter
import com.td.mylevelup.Constants
import com.td.mylevelup.models.alphaVantage.SymbolDayInformation
import okhttp3.*
import org.json.JSONObject
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.HashMap

class InvestingSimulationPresenter(private val selectedSymbol: String,
                                   private val view: InvestingSimulationView,
                                   private val client: OkHttpClient): AbstractPresenter() {
    private val priceMap: HashMap<String, SymbolDayInformation> = HashMap()

    override fun onViewReady() {
        super.onViewReady()

        val urlBuilder: HttpUrl.Builder = HttpUrl.parse("https://www.alphavantage.co/query")?.newBuilder() ?: HttpUrl.Builder()
        urlBuilder.addQueryParameter("symbol", selectedSymbol)
        urlBuilder.addQueryParameter("outputsize", "full")
        urlBuilder.addQueryParameter("function", "TIME_SERIES_DAILY")
        urlBuilder.addQueryParameter("apikey", Constants.ALPHA_VANTAGE_API_KEY)

        val request: Request = Request.Builder().url(urlBuilder.toString())
                .header("Authorization", Constants.ALPHA_VANTAGE_AUTH_TOKEN)
                .build()

        client.newCall(request).enqueue(object: Callback {
            override fun onResponse(call: Call?, response: Response?) {
                val responseBody: String = response?.body()?.string() ?: return
                val responseObject: JSONObject = JSONObject(responseBody).getJSONObject("Time Series (Daily)")
                val dateIterator: Iterator<String> = responseObject.keys()

                for (key in dateIterator) {
                    val dateString: String = key
                    val symbolDayInformation: SymbolDayInformation = Gson().fromJson(responseObject.getJSONObject(dateString).toString(),
                            SymbolDayInformation::class.java)

                    priceMap[dateString] = symbolDayInformation
                }
            }

            override fun onFailure(call: Call?, e: IOException?) {
                // TODO: Error Handling
            }
        })
    }
}