package com.td.mylevelup.creditCard.transactionDetails

import com.android.volley.VolleyError
import com.ngam.rvabstractions.presenter.AbstractPresenter
import com.td.virtualbank.VirtualBank
import com.td.virtualbank.VirtualBankCreditCardAccount
import com.td.virtualbank.VirtualBankGetTransactionsRequest
import com.td.virtualbank.VirtualBankTransaction
import java.util.ArrayList

class CreditCardTransactionDetailsPresenter(
        private val view: CreditCardTransactionDetailsView,
        private val vb: VirtualBank): AbstractPresenter() {

    lateinit var selectedCard: VirtualBankCreditCardAccount
    private var transactions: ArrayList<VirtualBankTransaction> = ArrayList()

    fun getTransactionsRequestClosure(): VirtualBankGetTransactionsRequest {
        return object: VirtualBankGetTransactionsRequest() {
            override fun onSuccess(p0: ArrayList<VirtualBankTransaction>?) {
                transactions = p0 ?: ArrayList()
                view.reloadTransactions()
                view.storeTransactions(selectedCard, p0)
            }

            override fun onError(p0: VolleyError?) {
                // TODO
            }
        }
    }

    fun onCardChanged(account: VirtualBankCreditCardAccount) {
        if (view.getTransactions(account) == null) {
            view.makeTransactionsCall(vb, account)
            return
        }
        selectedCard = account
        transactions = view.getTransactions(account) ?: return
        view.reloadTransactions()
    }

    fun getTransactions(): ArrayList<VirtualBankTransaction> {
        return transactions
    }

    fun getFormattedAccountString(): String {
        return String.format("Transactions for VISA - %s", selectedCard.maskedNumber)
    }
}