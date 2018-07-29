package com.td.mylevelup.accounts

import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.TextView
import com.ngam.rvabstractions.activity.AbstractActivity
import com.ngam.rvabstractions.properties.AbstractClassProperties
import com.td.mylevelup.Constants
import com.td.mylevelup.R
import com.td.mylevelup.VirtualBankInformationHolder
import com.td.mylevelup.accounts.bottomView.AccountsBottomAdapter
import com.td.mylevelup.accounts.bottomView.AccountsBottomPresenter
import com.td.mylevelup.accounts.bottomView.AccountsBottomView
import com.td.mylevelup.accounts.bottomView.AccountsBottomViewDelegate
import com.td.mylevelup.accounts.topView.AccountsTopAdapter
import com.td.mylevelup.accounts.topView.AccountsTopPresenter
import com.td.mylevelup.accounts.topView.AccountsTopView
import com.td.mylevelup.accounts.topView.AccountsTopViewDelegate
import com.td.virtualbank.VirtualBank
import com.td.virtualbank.VirtualBankBankAccount
import com.td.virtualbank.VirtualBankTransaction
import java.util.ArrayList

class AccountsActivity: AbstractActivity<AccountsBottomPresenter, AccountsBottomAdapter>(),
        AccountsTopView, AccountsBottomView, AccountsTopViewDelegate, AccountsBottomViewDelegate {
    private lateinit var titleText: TextView
    private lateinit var vb: VirtualBank
    private lateinit var topListView: RecyclerView
    private lateinit var topPresenter: AccountsTopPresenter
    private lateinit var topAdapter: AccountsTopAdapter

    override fun setProperties(): AbstractClassProperties<AccountsBottomPresenter, AccountsBottomAdapter> {
        vb = VirtualBank.getBank(Constants.AUTH_TOKEN)
        presenter = AccountsBottomPresenter(this)
        adapter = AccountsBottomAdapter(presenter)
        return AbstractClassProperties(presenter, adapter, "Your Accounts")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Back Button on Nav Bar. If bar null, no back button. User can use physical back.
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Title
        titleText = findViewById(R.id.centerTitle)
        titleText.text = getString(R.string.balances_title)
        titleText.textSize = 40f

        // Setup top cards
        topListView = findViewById(R.id.accountsPageCard)
        topPresenter = AccountsTopPresenter(this, vb, this)
        topAdapter = AccountsTopAdapter(topPresenter)

        topListView.apply {
            layoutManager = LinearLayoutManager(this@AccountsActivity, LinearLayoutManager.HORIZONTAL, false)
            itemAnimator = DefaultItemAnimator()
            adapter = topAdapter
        }

        topAdapter.buildRows()
        topPresenter.onViewReady()
    }

    override fun getLayoutId(): Int {
        return R.layout.accounts_activity
    }

    override fun getRecyclerViewId(): Int {
        return R.id.accountsPageBottom
    }

    override fun reloadTop() {
        topAdapter.reload()
    }

    override fun notifyTopLoaded(accounts: ArrayList<VirtualBankBankAccount>) {
        topViewLoaded(accounts)
    }

    override fun getBankAccounts(): ArrayList<VirtualBankBankAccount>? {
        return VirtualBankInformationHolder.bankAccounts
    }

    override fun storeBankAccountsResponse(accounts: ArrayList<VirtualBankBankAccount>?) {
        VirtualBankInformationHolder.bankAccounts = accounts
    }

    override fun makeBankAccountsCall(vb: VirtualBank) {
        vb.getCustomerBankAccounts(this, Constants.IVANA_EASTOM_STUDENT_ID,
                topPresenter.createBankAccountsClosure())
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    override fun onAccountSelected(account: VirtualBankBankAccount) {
        if (presenter.selectedAccount == account) {
            return
        }

        presenter.selectedAccount = account
        if (getTransactions(presenter.selectedAccount) == null) {
            makeTransactionsCall(vb, account)
            return
        }
        presenter.handleTransactions(VirtualBankInformationHolder.bankAccountTransactions[presenter.selectedAccount])
        listView.scrollToPosition(0)
    }

    override fun topViewLoaded(accounts: ArrayList<VirtualBankBankAccount>) {
        presenter.accounts = accounts
        presenter.selectedAccount = accounts.first()
        adapter.reload()
        if (getTransactions(presenter.selectedAccount) == null) {
            makeTransactionsCall(vb, presenter.selectedAccount)
            return
        }
        presenter.handleTransactions(VirtualBankInformationHolder.bankAccountTransactions[presenter.selectedAccount])
    }

    override fun reloadBottom() {
        adapter.reload()
    }

    override fun getTransactions(account: VirtualBankBankAccount): ArrayList<VirtualBankTransaction>? {
        return VirtualBankInformationHolder.bankAccountTransactions[account]
    }

    override fun makeTransactionsCall(vb: VirtualBank, account: VirtualBankBankAccount) {
        vb.getTransactions(this, account.id, presenter.getTransactionsClosure())
    }

    override fun storeTransactionResponse(account: VirtualBankBankAccount, transactions: ArrayList<VirtualBankTransaction>?) {
        VirtualBankInformationHolder.bankAccountTransactions[account] = transactions
    }
}