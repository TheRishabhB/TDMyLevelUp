package com.td.mylevelup.accounts.topView

import com.android.volley.VolleyError
import com.ngam.rvabstractions.contracts.OnInputReceived
import com.ngam.rvabstractions.presenter.AbstractPresenter
import com.td.virtualbank.VirtualBank
import com.td.virtualbank.VirtualBankBankAccount
import com.td.virtualbank.VirtualBankGetCustomerBankAccountsRequest
import java.util.ArrayList

class AccountsTopPresenter(private val view: AccountsTopView,
                           private val vb: VirtualBank,
                           private val delegate: AccountsTopViewDelegate): AbstractPresenter() {
    private var accounts: ArrayList<VirtualBankBankAccount> = ArrayList()

    override fun onViewReady() {
        super.onViewReady()
        if (view.getBankAccounts() == null) {
            view.makeBankAccountsCall(vb)
            return
        }
        handleBankAccountsResponse(view.getBankAccounts())
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
        view.storeBankAccountsResponse(accounts)
        view.reloadTop()
        view.notifyTopLoaded(accounts)
    }

    fun getAccounts(): ArrayList<VirtualBankBankAccount> {
        return accounts
    }

    fun createAccountInputListener(): OnInputReceived<VirtualBankBankAccount> {
        return object: OnInputReceived<VirtualBankBankAccount> {
            override fun onInputReceived(input: VirtualBankBankAccount) {
                delegate.onAccountSelected(input)
            }
        }
    }
}