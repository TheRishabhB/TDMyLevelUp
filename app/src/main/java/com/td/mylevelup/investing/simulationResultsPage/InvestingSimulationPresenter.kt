package com.td.mylevelup.investing.simulationResultsPage

import com.android.volley.VolleyError
import com.google.gson.Gson
import com.ngam.rvabstractions.general.AbstractPresenter
import com.td.mylevelup.Constants
import com.td.mylevelup.VirtualBankInformationHolder
import com.td.mylevelup.models.InvestingSimulationTransaction
import com.td.mylevelup.models.alphaVantage.SymbolDayInformation
import com.td.virtualbank.*
import okhttp3.*
import org.json.JSONObject
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class InvestingSimulationPresenter(private val selectedSymbol: String,
                                   private val view: InvestingSimulationView,
                                   private val client: OkHttpClient): AbstractPresenter() {
    private val priceMap: HashMap<String, SymbolDayInformation> = HashMap()
    private var bankingAccounts: ArrayList<VirtualBankBankAccount> = ArrayList()
    private var creditCardAccounts: ArrayList<VirtualBankCreditCardAccount> = ArrayList()
    private var transactions: ArrayList<VirtualBankTransaction> = ArrayList()
    private val trades: ArrayList<InvestingSimulationTransaction> = ArrayList()
    private var expenses: Double = 0.00
    private var numCallsMade: Int = 0
    private var numCallsBack: Int = 0
    private var numberOfShares: Double = 0.0

    override fun onViewReady() {
        super.onViewReady()
        makeAlphaVantageSymbolCall()
    }

    private fun makeAlphaVantageSymbolCall() {
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
                makeBankingAccountsCall()
                makeCreditCardAccountsCall()
            }

            override fun onFailure(call: Call?, e: IOException?) {
                // TODO: Error Handling
            }
        })
    }

    private fun makeBankingAccountsCall() {
        if (VirtualBankInformationHolder.bankAccounts == null) {
            view.makeBankingAccountsCall()
            return
        }

        bankingAccounts = VirtualBankInformationHolder.bankAccounts ?: ArrayList()
        for (account in bankingAccounts) {
            makeTransactionsCall(account)
        }
    }

    private fun makeCreditCardAccountsCall() {
        if (VirtualBankInformationHolder.creditCardAccounts == null) {
            view.makeCreditCardAccountsCall()
            return
        }

        creditCardAccounts = VirtualBankInformationHolder.creditCardAccounts ?: ArrayList()

        for (account in creditCardAccounts) {
            makeTransactionsCall(account)
        }
    }

    private fun makeTransactionsCall(account: VirtualBankBankAccount) {
        view.makeTransactionsCall(account.id)
    }

    private fun makeTransactionsCall(account: VirtualBankCreditCardAccount) {
        view.makeTransactionsCall(account.id)
    }

    @Synchronized private fun analyzeTransactions() {
        val expenses: List<VirtualBankTransaction> = transactions.filter { it.currencyAmount < 0 }
        if (expenses.isEmpty()) {
            return
        }
        this.expenses += expenses.sumByDouble { -it.currencyAmount }
        numberOfShares = 0.0

        val expenseDateFormat = SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss", Locale.ENGLISH)
        val symbolDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)

        for (expense in expenses) {
            val expenseDate: Date = expenseDateFormat.parse(expense.originationDate)
            val symbolDate: String = symbolDateFormat.format(expenseDate)
            val symbolDayInfo: SymbolDayInformation = priceMap[symbolDate] ?: continue
            val price: Double = symbolDayInfo.close.toDouble()
            val sharesToBuy: Double = -expense.currencyAmount/price
            numberOfShares += sharesToBuy
            val amountHeld: Double = price * sharesToBuy
            trades.add(InvestingSimulationTransaction(selectedSymbol,
                    getTransactionName(expense), sharesToBuy, price, amountHeld, symbolDate))
        }
        view.reloadResults()
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

    fun getPriceMap(): HashMap<String, SymbolDayInformation> {
        return priceMap
    }

    fun getSymbolTrades(): ArrayList<InvestingSimulationTransaction> {
        return trades
    }

    fun getTotalExpenses(): Double {
        return expenses
    }

    fun getTotalGrowth(): Double {
        val symbolDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
        val calendar: Calendar = Calendar.getInstance()
        calendar.add(Calendar.DATE, -1)
        return numberOfShares * (priceMap[symbolDateFormat.format(calendar.time)]?.close?.toDouble() ?: 0.00)
    }

    fun getSymbol(): String {
        return selectedSymbol
    }

    fun createGetAccountsClosure(): VirtualBankGetCustomerBankAccountsRequest {
        return object: VirtualBankGetCustomerBankAccountsRequest() {
            override fun onSuccess(p0: ArrayList<VirtualBankBankAccount>?) {
                bankingAccounts = p0 ?: return
                VirtualBankInformationHolder.bankAccounts = bankingAccounts
                makeBankingAccountsCall()
            }

            override fun onError(p0: VolleyError?) {
                // TODO: Handle Error
            }
        }
    }

    fun createGetCreditCardAccountsClosure(): VirtualBankGetCustomerCreditCardAccountsRequest {
        return object: VirtualBankGetCustomerCreditCardAccountsRequest() {
            override fun onSuccess(p0: ArrayList<VirtualBankCreditCardAccount>?) {
                creditCardAccounts = p0 ?: return
                VirtualBankInformationHolder.creditCardAccounts = creditCardAccounts
                makeCreditCardAccountsCall()
            }

            override fun onError(p0: VolleyError?) {
                // TODO: Handle Error
            }
        }
    }

    fun createGetTransactionsClosure(): VirtualBankGetTransactionsRequest {
        numCallsMade++
        return object: VirtualBankGetTransactionsRequest() {
            override fun onSuccess(p0: ArrayList<VirtualBankTransaction>?) {
                numCallsBack++
                val transactions: ArrayList<VirtualBankTransaction> = p0 ?: return
                this@InvestingSimulationPresenter.transactions.addAll(transactions)
                analyzeTransactions()
            }

            override fun onError(p0: VolleyError?) {
                numCallsBack++
                // TODO: Handle Error
            }
        }
    }
}