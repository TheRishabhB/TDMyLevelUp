package com.td.mylevelup.creditCard.transactionDetails

import com.android.volley.VolleyError
import com.ngam.rvabstractions.general.AbstractPresenter
import com.td.mylevelup.models.CreditCardsDetails
import com.td.virtualbank.VirtualBank
import com.td.virtualbank.VirtualBankCreditCardAccount
import com.td.virtualbank.VirtualBankGetTransactionsRequest
import com.td.virtualbank.VirtualBankTransaction
import java.util.ArrayList

class CreditCardTransactionDetailsPresenter(
        private val view: CreditCardTransactionDetailsView,
        private val vb: VirtualBank): AbstractPresenter() {

    var errorState: Boolean = false
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
                errorState = true
                view.reloadTransactions()
            }
        }
    }

    fun onCardChanged(account: VirtualBankCreditCardAccount) {
        errorState = false
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

    fun getCreditCardRecommendations(): ArrayList<CreditCardsDetails> {
        val list: ArrayList<CreditCardsDetails> = ArrayList()
        list.add(CreditCardsDetails.US_DOLLAR)
        list.add(CreditCardsDetails.REWARDS)
        list.add(CreditCardsDetails.PLATINUM_TRAVEL)
        list.add(CreditCardsDetails.FIRST_CLASS_TRAVEL_VISA_INFINITE)
        list.add(CreditCardsDetails.EMERALD_FLEX_RATE)
        list.add(CreditCardsDetails.CASH_BACK_VISA_INFINITE)
        list.add(CreditCardsDetails.AEROPLAN_VISA_PRIVILEGE)
        list.add(CreditCardsDetails.AEROPLAN_VISA_PLATINUM)
        list.add(CreditCardsDetails.AEROPLAN_VISA_INFINITE)
        return list
    }
}