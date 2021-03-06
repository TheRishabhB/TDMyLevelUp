package com.td.mylevelup.dashboard.creditCardCard

import android.content.Context
import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.util.TypedValue
import com.ngam.rvabstractions.singleCard.CardDataSource
import com.ngam.rvabstractions.singleCard.GenericCardView
import com.td.mylevelup.Constants
import com.td.mylevelup.R
import com.td.mylevelup.VirtualBankInformationHolder
import com.td.mylevelup.creditCard.CreditCardActivity
import com.td.virtualbank.VirtualBank
import com.td.virtualbank.VirtualBankCreditCardAccount
import java.util.ArrayList

class CreditCardCard(context: Context, attrSet: AttributeSet?, defStyleAttr: Int):
        GenericCardView<CreditCardCardPresenter, CreditCardCardAdapter>(context, attrSet, defStyleAttr),
        CreditCardCardView {
    // Constructors for LinearLayout
    constructor(context: Context, attrSet: AttributeSet?): this(context, attrSet, 0)
    constructor(context: Context): this(context, null)

    override fun setDataSource(): CardDataSource<CreditCardCardPresenter, CreditCardCardAdapter> {
        val vb: VirtualBank = VirtualBank.getBank(Constants.AUTH_TOKEN, context)
        presenter = CreditCardCardPresenter(this, vb)
        adapter = CreditCardCardAdapter(presenter)
        return CardDataSource(presenter, adapter, "Credit Cards:",
                R.drawable.credit_card_icon, presenter.createCardBannerClickListener())
    }

    init {
        cardListView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        cardListView.setPadding(getPixelsGivenDP(80f), getPixelsGivenDP(15f), 0, 0)
        cardListView.setBackgroundResource(R.drawable.td_couch)
        cardListView.layoutParams.height = getPixelsGivenDP(200f)
        cardListView.addOnScrollListener(createRVScrollListener())
    }

    private fun getPixelsGivenDP(dp: Float): Int {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, resources.displayMetrics).toInt()
    }

    private fun createRVScrollListener(): RecyclerView.OnScrollListener {
        return object: RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy > 0) {
                    cardListView.setPadding(getPixelsGivenDP(0f), getPixelsGivenDP(15f), 0, 0)
                    return
                }
                cardListView.setPadding(getPixelsGivenDP(80f), getPixelsGivenDP(15f), 0, 0)
            }
        }
    }

    fun dataChanged() {
        presenter.onViewReady()
        adapter.reload()
    }

    fun dataRefreshed() {
        presenter.updateCardData()
        reloadCard()
    }

    override fun launchCreditCardDetailsPage() {
        val intent = Intent(context, CreditCardActivity::class.java)
        context.startActivity(intent)
    }

    override fun getCreditCardAccounts(): ArrayList<VirtualBankCreditCardAccount>? {
        return VirtualBankInformationHolder.creditCardAccounts
    }

    override fun makeCreditCardAccountsCall(vb: VirtualBank) {
        vb.getCustomerCreditCardAccounts(context, Constants.SELECTED_PROFILE.profile.id, presenter.getCreditAccountsClosure())
    }

    override fun reloadCard() {
        adapter.reload()
    }

    override fun storeResponse(accounts: ArrayList<VirtualBankCreditCardAccount>?) {
        VirtualBankInformationHolder.creditCardAccounts = accounts
    }
}