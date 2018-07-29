package com.td.mylevelup.accounts.bottomView

import com.td.virtualbank.VirtualBank
import com.td.virtualbank.VirtualBankBankAccount
import com.td.virtualbank.VirtualBankTransaction
import java.util.ArrayList

interface AccountsBottomView {
    fun reloadBottom()
    fun storeTransactionResponse(account: VirtualBankBankAccount, transactions: ArrayList<VirtualBankTransaction>?)
    fun getTransactions(account: VirtualBankBankAccount): ArrayList<VirtualBankTransaction>?
    fun makeTransactionsCall(vb: VirtualBank, account: VirtualBankBankAccount)
}