package com.td.mylevelup.investing.simulationResultsPage

interface InvestingSimulationView {
    fun reloadResults()
    fun makeBankingAccountsCall()
    fun makeCreditCardAccountsCall()
    fun makeTransactionsCall(id: String)
}