package com.td.mylevelup.dashboard.accountsCard

import android.content.Context
import android.content.Intent
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.util.AttributeSet
import com.ngam.rvabstractions.singleCard.CardDataSource
import com.ngam.rvabstractions.singleCard.GenericCardView
import com.td.mylevelup.Constants
import com.td.mylevelup.R
import com.td.mylevelup.VirtualBankInformationHolder
import com.td.mylevelup.accounts.AccountsActivity
import com.td.virtualbank.VirtualBank
import com.td.virtualbank.VirtualBankBankAccount
import java.util.ArrayList

class AccountsCard(context: Context, attrSet: AttributeSet?, defStyleAttr: Int):
        GenericCardView<AccountsCardPresenter, AccountsCardAdapter>(context, attrSet, defStyleAttr), AccountsCardView {
    // Constructors for LinearLayout
    constructor(context: Context, attrSet: AttributeSet?): this(context, attrSet, 0)
    constructor(context: Context): this(context, null)

    override fun setDataSource(): CardDataSource<AccountsCardPresenter, AccountsCardAdapter> {
        val vb: VirtualBank = VirtualBank.getBank(Constants.AUTH_TOKEN)
        presenter = AccountsCardPresenter(this, vb)
        adapter = AccountsCardAdapter(presenter)
        return CardDataSource(presenter, adapter, "Banking Accounts:",
                R.drawable.accounts_icon, presenter.createBannerClickListener())
    }

    init {
        cardListView.setBackgroundColor(ContextCompat.getColor(context, R.color.background))
        cardListView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
    }

    fun dataChanged() {
        presenter.onViewReady()
        reloadCard()
    }

    fun dataRefreshed() {
        presenter.updateCardData()
        reloadCard()
    }

    override fun reloadCard() {
        adapter.reload()
    }

    override fun storeResponse(accounts: ArrayList<VirtualBankBankAccount>?) {
        VirtualBankInformationHolder.bankAccounts = accounts
    }

    override fun makeBankAccountsCall(vb: VirtualBank) {
        vb.getCustomerBankAccounts(context, Constants.SELECTED_PROFILE.profile.id, presenter.createBankAccountsClosure())
    }

    override fun getBankAccounts(): ArrayList<VirtualBankBankAccount>? {
        return VirtualBankInformationHolder.bankAccounts
    }

    override fun launchAccountsDetailsPage() {
        val intent = Intent(context, AccountsActivity::class.java)
        context.startActivity(intent)
    }
}