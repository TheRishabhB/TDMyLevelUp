package com.td.mylevelup.creditCard.transactionDetails

import com.td.virtualbank.VirtualBank
import com.td.virtualbank.VirtualBankCreditCardAccount
import com.td.virtualbank.VirtualBankTransaction
import java.util.ArrayList

interface CreditCardTransactionDetailsView {
    fun reloadTransactions()
    fun getTransactions(account: VirtualBankCreditCardAccount): ArrayList<VirtualBankTransaction>?
    fun storeTransactions(account: VirtualBankCreditCardAccount, transactions: ArrayList<VirtualBankTransaction>?)
    fun makeTransactionsCall(vb: VirtualBank, account: VirtualBankCreditCardAccount)

}