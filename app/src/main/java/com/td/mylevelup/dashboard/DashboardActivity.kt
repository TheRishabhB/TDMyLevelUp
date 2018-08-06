package com.td.mylevelup.dashboard

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ngam.rvabstractions.general.OnInputReceived
import com.td.mylevelup.Constants
import com.td.mylevelup.R
import com.td.mylevelup.VirtualBankInformationHolder
import com.td.mylevelup.dashboard.accountsCard.AccountsCard
import com.td.mylevelup.dashboard.creditCardCard.CreditCardCard
import com.td.mylevelup.dashboard.profileSwitcher.ProfileSwitcherView
import com.td.mylevelup.models.CustomerProfiles
import com.td.mylevelup.models.SearchSymbolModel
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class DashboardActivity : AppCompatActivity() {

    private lateinit var accountsCardView: AccountsCard
    private lateinit var creditCardCard: CreditCardCard

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        supportActionBar?.hide()

        val switcher: ProfileSwitcherView = findViewById(R.id.profileSwitcherView)
        switcher.attachScrollListener(createScrollListener())

        accountsCardView = findViewById(R.id.accountsCard)
        creditCardCard = findViewById(R.id.creditCardCard)

        val urlBuilder: HttpUrl.Builder = HttpUrl.parse("http://d.yimg.com/autoc.finance.yahoo.com/autoc")?.newBuilder() ?: HttpUrl.Builder()
        urlBuilder.addQueryParameter("query", "ms")
        urlBuilder.addQueryParameter("region", "1")
        urlBuilder.addQueryParameter("lang", "en")

        val request: Request = Request.Builder().url(urlBuilder.toString()).build()

        val client = OkHttpClient()

        client.newCall(request).enqueue(object: Callback {
            override fun onResponse(call: Call?, response: Response?) {
                val responseString: String = JSONObject(response?.body()?.string().toString())
                        .getJSONObject("ResultSet")
                        .getJSONArray("Result")
                        .toString()
                val listResponseTypeToken =  object: TypeToken<List<SearchSymbolModel>>(){}.type

                val stockList: List<SearchSymbolModel> = Gson().fromJson(responseString, listResponseTypeToken)
            }

            override fun onFailure(call: Call?, e: IOException?) {
                // TODO: Handle Fail
            }
        })
    }

    private fun createScrollListener(): OnInputReceived<CustomerProfiles> {
        return object: OnInputReceived<CustomerProfiles> {
            override fun onInputReceived(input: CustomerProfiles) {
                Constants.SELECTED_PROFILE = input
                // TODO: Proper Caching for multiple users. At the moment only caches for one user.
                VirtualBankInformationHolder.clearAllValues()
                accountsCardView.dataChanged()
                creditCardCard.dataChanged()
            }
        }
    }
}