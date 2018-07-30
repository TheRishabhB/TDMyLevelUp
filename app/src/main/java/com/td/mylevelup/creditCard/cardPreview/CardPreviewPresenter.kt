package com.td.mylevelup.creditCard.cardPreview

import com.android.volley.VolleyError
import com.ngam.rvabstractions.contracts.OnInputReceived
import com.ngam.rvabstractions.presenter.AbstractPresenter
import com.td.virtualbank.VirtualBank
import com.td.virtualbank.VirtualBankCreditCardAccount
import com.td.virtualbank.VirtualBankGetCustomerCreditCardAccountsRequest
import java.util.ArrayList

class CardPreviewPresenter(private val view: CreditCardPreviewView,
                           private val vb: VirtualBank): AbstractPresenter() {
    private var accounts: ArrayList<VirtualBankCreditCardAccount> = ArrayList()

    override fun onViewReady() {
        // If no cached value of CC accounts, make call.
        if (view.getCreditCardAccounts() == null) {
            view.makeCreditCardAccountsCall(vb)
            return
        }
        handleCreditCardAccountsResponse(view.getCreditCardAccounts())
    }

    fun getCreditCardChangedClosure(): OnInputReceived<VirtualBankCreditCardAccount> {
        return object: OnInputReceived<VirtualBankCreditCardAccount> {
            override fun onInputReceived(input: VirtualBankCreditCardAccount) {
                view.cardChanged(input)
            }
        }
    }

    fun getCreditAccountsClosure(): VirtualBankGetCustomerCreditCardAccountsRequest {
        return object: VirtualBankGetCustomerCreditCardAccountsRequest() {
            override fun onSuccess(p0: ArrayList<VirtualBankCreditCardAccount>?) {
                handleCreditCardAccountsResponse(p0)
                view.storeResponse(p0 ?: return)
            }

            override fun onError(p0: VolleyError?) {
                // TODO
            }
        }
    }

    private fun handleCreditCardAccountsResponse(accounts: ArrayList<VirtualBankCreditCardAccount>?) {
        this.accounts = accounts ?: return
        view.reloadDetailsCard()
        view.previewCardLoaded(accounts)
    }

    fun getAccounts(): ArrayList<VirtualBankCreditCardAccount> {
        return accounts
    }
}