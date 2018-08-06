package com.td.mylevelup.investing.searchSymbolsPage

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ngam.rvabstractions.general.AbstractPresenter
import com.ngam.rvabstractions.general.OnInputReceived
import com.td.mylevelup.models.yahooAPI.SearchSymbolModel
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class InvestingPagePresenter(private val view: InvestingPageView,
                             private val client: OkHttpClient): AbstractPresenter() {
    private var symbolsList: MutableList<SearchSymbolModel> = ArrayList()
    private var searchQuery: String? = null

    fun getUpdatedSymbolsList(): List<SearchSymbolModel> {
        return symbolsList.filter { it.exchDisp == "NASDAQ" && it.typeDisp == "Equity" }
    }

    fun getUpdatedSearchQuery(): String? {
        return searchQuery
    }

    fun setSearchQueryAndSearch(query: String) {
        searchQuery = query

        val urlBuilder: HttpUrl.Builder = HttpUrl.parse("http://d.yimg.com/autoc.finance.yahoo.com/autoc")?.newBuilder() ?: HttpUrl.Builder()
        urlBuilder.addQueryParameter("query", searchQuery)
        urlBuilder.addQueryParameter("region", "1")
        urlBuilder.addQueryParameter("lang", "en")
        val request: Request = Request.Builder().url(urlBuilder.toString()).build()

        client.newCall(request).enqueue(object: Callback {
            override fun onResponse(call: Call?, response: Response?) {
                val responseBody: String = response?.body()?.string() ?: return

                val queryString: String = JSONObject(responseBody)
                        .getJSONObject("ResultSet")
                        .getString("Query")

                if (queryString != searchQuery) {
                    // Only update if is most up-to-date string
                    return
                }

                val responseString: String = JSONObject(responseBody)
                        .getJSONObject("ResultSet")
                        .getJSONArray("Result")
                        .toString()

                val listResponseTypeToken =  object: TypeToken<List<SearchSymbolModel>>(){}.type

                symbolsList = Gson().fromJson(responseString, listResponseTypeToken)
                view.reloadSymbolsList()
            }

            override fun onFailure(call: Call?, e: IOException?) {
                // TODO: Handle Fail
            }
        })
    }

    fun createSearchInputListener(): OnInputReceived<String> {
        return object: OnInputReceived<String> {
            override fun onInputReceived(input: String) {
                view.launchSimulationScreenWithSymbol(input)
            }
        }
    }
}