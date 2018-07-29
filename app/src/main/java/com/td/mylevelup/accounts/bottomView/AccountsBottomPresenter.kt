package com.td.mylevelup.accounts.bottomView

import com.android.volley.VolleyError
import com.ngam.rvabstractions.presenter.AbstractPresenter
import com.td.mylevelup.models.PersonalAccountsEnum
import com.td.virtualbank.VirtualBankBankAccount
import com.td.virtualbank.VirtualBankGetTransactionsRequest
import com.td.virtualbank.VirtualBankTransaction
import java.util.ArrayList

class AccountsBottomPresenter(private val view: AccountsBottomView): AbstractPresenter() {
    var accounts: ArrayList<VirtualBankBankAccount> = ArrayList()
    lateinit var selectedAccount: VirtualBankBankAccount
    lateinit var transactions: ArrayList<VirtualBankTransaction>
    var shouldShowShimmer: Boolean = true

    fun getAccountRecommendations(): PersonalAccountsEnum {
        val accountBalances: Double = accounts.sumByDouble { it.balance }
        when (accountBalances) {
            in 2000..3000 -> return PersonalAccountsEnum.MINIMUM_CHEQUING
            in 3000..4000 -> return PersonalAccountsEnum.EVERYDAY_CHEQUING
            in 4000..5000 -> return PersonalAccountsEnum.UNLIMITED_CHEQUING
            in 5000..Int.MAX_VALUE -> PersonalAccountsEnum.ALL_INCLUSIVE_BANKING
            else -> return PersonalAccountsEnum.MINIMUM_CHEQUING
        }
        return PersonalAccountsEnum.MINIMUM_CHEQUING
    }

    fun getTransactionsClosure(): VirtualBankGetTransactionsRequest {
        return object: VirtualBankGetTransactionsRequest() {
            override fun onSuccess(p0: ArrayList<VirtualBankTransaction>?) {
                handleTransactions(p0)
                view.storeTransactionResponse(selectedAccount, p0)
            }

            override fun onError(p0: VolleyError?) {
                // TODO
            }
        }
    }

    fun handleTransactions(transactions: ArrayList<VirtualBankTransaction>?) {
        this.transactions = transactions ?: return
        shouldShowShimmer = false
        view.reloadBottom()
    }

    fun getFormattedAccountString(): String {
        return String.format("Transactions for %1s - %2s", selectedAccount.type, selectedAccount.maskedAccountNumber)
    }
}