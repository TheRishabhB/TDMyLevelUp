package com.td.mylevelup.dashboard.accountsCard

import android.view.View
import com.android.volley.VolleyError
import com.ngam.rvabstractions.general.AbstractPresenter
import com.ngam.rvabstractions.general.OnInputReceived
import com.td.virtualbank.VirtualBank
import com.td.virtualbank.VirtualBankBankAccount
import com.td.virtualbank.VirtualBankGetCustomerBankAccountsRequest
import java.util.ArrayList

class AccountsCardPresenter(private val view: AccountsCardView,
                            private val vb: VirtualBank): AbstractPresenter() {

    private var accounts: ArrayList<VirtualBankBankAccount> = ArrayList()
    var isError: Boolean = false

    override fun onViewReady() {
        super.onViewReady()
        accounts.clear()
        isError = false
        if (view.getBankAccounts() == null) {
            view.makeBankAccountsCall(vb)
            return
        }
        handleBankAccountsResponse(view.getBankAccounts())
    }

    fun createBannerClickListener(): View.OnClickListener {
        return View.OnClickListener {
            view.launchAccountsDetailsPage()
        }
    }

    fun createAccountInputListener(): OnInputReceived<VirtualBankBankAccount> {
        return object: OnInputReceived<VirtualBankBankAccount> {
            override fun onInputReceived(input: VirtualBankBankAccount) {
                view.launchAccountsDetailsPage()
            }
        }
    }

    fun createBankAccountsClosure(): VirtualBankGetCustomerBankAccountsRequest {
        return object: VirtualBankGetCustomerBankAccountsRequest() {
            override fun onSuccess(p0: ArrayList<VirtualBankBankAccount>?) {
                isError = false
                handleBankAccountsResponse(p0)
            }

            override fun onError(p0: VolleyError?) {
                isError = true
            }
        }
    }

    private fun handleBankAccountsResponse(accounts: ArrayList<VirtualBankBankAccount>?) {
        this.accounts = accounts ?: return
        isError = false
        view.storeResponse(accounts)
        view.reloadCard()
    }

    fun getAccounts(): ArrayList<VirtualBankBankAccount> {
        return accounts
    }
}