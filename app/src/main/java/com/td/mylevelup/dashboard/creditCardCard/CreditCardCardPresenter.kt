package com.td.mylevelup.dashboard.creditCardCard

import android.view.View
import com.android.volley.VolleyError
import com.ngam.rvabstractions.general.AbstractPresenter
import com.ngam.rvabstractions.general.OnInputReceived
import com.td.virtualbank.VirtualBank
import com.td.virtualbank.VirtualBankCreditCardAccount
import com.td.virtualbank.VirtualBankGetCustomerCreditCardAccountsRequest
import java.util.ArrayList

class CreditCardCardPresenter(
        private val view: CreditCardCardView,
        private val vb: VirtualBank): AbstractPresenter() {

    private var accounts: ArrayList<VirtualBankCreditCardAccount> = ArrayList()
    var isError: Boolean = false

    override fun onViewReady() {
        super.onViewReady()
        isError = false
        accounts.clear()
        if (view.getCreditCardAccounts() == null) {
            view.makeCreditCardAccountsCall(vb)
            return
        }
        handleCreditCardAccountsResponse(view.getCreditCardAccounts())
    }

    fun updateCardData() {
        isError = false
        if (view.getCreditCardAccounts() == null) {
            view.makeCreditCardAccountsCall(vb)
            return
        }
        handleCreditCardAccountsResponse(view.getCreditCardAccounts())
    }

    fun createCardBannerClickListener(): View.OnClickListener {
        return View.OnClickListener {
            view.launchCreditCardDetailsPage()
        }
    }

    fun createCardInputListener(): OnInputReceived<VirtualBankCreditCardAccount> {
        return object: OnInputReceived<VirtualBankCreditCardAccount> {
            override fun onInputReceived(input: VirtualBankCreditCardAccount) {
                view.launchCreditCardDetailsPage()
            }
        }
    }

    fun getCreditAccountsClosure(): VirtualBankGetCustomerCreditCardAccountsRequest {
        return object: VirtualBankGetCustomerCreditCardAccountsRequest() {
            override fun onSuccess(p0: ArrayList<VirtualBankCreditCardAccount>?) {
                handleCreditCardAccountsResponse(p0)
                isError = false
            }

            override fun onError(p0: VolleyError?) {
                isError = true
            }
        }
    }

    private fun handleCreditCardAccountsResponse(accounts: ArrayList<VirtualBankCreditCardAccount>?) {
        this.accounts = accounts ?: return
        isError = false
        view.storeResponse(accounts)
        view.reloadCard()
    }

    fun getAccounts(): java.util.ArrayList<VirtualBankCreditCardAccount> {
        return accounts
    }
}