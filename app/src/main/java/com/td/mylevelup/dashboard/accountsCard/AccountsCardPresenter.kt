package com.td.mylevelup.dashboard.accountsCard

import android.view.View
import com.android.volley.VolleyError
import com.ngam.rvabstractions.presenter.AbstractPresenter
import com.td.virtualbank.VirtualBank
import com.td.virtualbank.VirtualBankBankAccount
import com.td.virtualbank.VirtualBankGetCustomerBankAccountsRequest
import java.util.ArrayList

class AccountsCardPresenter(private val view: AccountsCardView,
                            private val vb: VirtualBank): AbstractPresenter() {

    private var accounts: ArrayList<VirtualBankBankAccount> = ArrayList()

    override fun onViewReady() {
        super.onViewReady()
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

    fun createBankAccountsClosure(): VirtualBankGetCustomerBankAccountsRequest {
        return object: VirtualBankGetCustomerBankAccountsRequest() {
            override fun onSuccess(p0: ArrayList<VirtualBankBankAccount>?) {
                handleBankAccountsResponse(p0)
            }

            override fun onError(p0: VolleyError?) {
                // TODO: Handle Error
            }
        }
    }

    private fun handleBankAccountsResponse(accounts: ArrayList<VirtualBankBankAccount>?) {
        this.accounts = accounts ?: return
        view.storeResponse(accounts)
        view.reloadCard()
    }

    fun getAccounts(): ArrayList<VirtualBankBankAccount> {
        return accounts
    }
}