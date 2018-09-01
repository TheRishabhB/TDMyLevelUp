package com.td.mylevelup.creditCard

import android.os.Bundle
import android.support.v7.widget.*
import android.util.TypedValue
import android.widget.TextView
import com.ngam.rvabstractions.screens.AbstractActivity
import com.ngam.rvabstractions.screens.AbstractClassProperties
import com.td.mylevelup.Constants
import com.td.mylevelup.R
import com.td.mylevelup.VirtualBankInformationHolder
import com.td.mylevelup.creditCard.cardPreview.CardPreviewAdapter
import com.td.mylevelup.creditCard.cardPreview.CardPreviewPresenter
import com.td.mylevelup.creditCard.transactionDetails.CreditCardTransactionDetailsAdapter
import com.td.mylevelup.creditCard.transactionDetails.CreditCardTransactionDetailsPresenter
import com.td.mylevelup.creditCard.cardPreview.CreditCardPreviewView
import com.td.mylevelup.creditCard.transactionDetails.CreditCardTransactionDetailsView
import com.td.virtualbank.VirtualBank
import com.td.virtualbank.VirtualBankCreditCardAccount
import com.td.virtualbank.VirtualBankTransaction
import java.util.ArrayList

class CreditCardActivity: AbstractActivity<CreditCardTransactionDetailsPresenter, CreditCardTransactionDetailsAdapter>(),
        CreditCardPreviewView, CreditCardTransactionDetailsView {
    private lateinit var titleText: TextView
    private lateinit var cardPreviewListView: RecyclerView
    private lateinit var previewPresenter: CardPreviewPresenter
    private lateinit var previewAdapter: CardPreviewAdapter
    private lateinit var vb: VirtualBank

    override fun setProperties(): AbstractClassProperties<CreditCardTransactionDetailsPresenter, CreditCardTransactionDetailsAdapter> {
        vb = VirtualBank.getBank(Constants.AUTH_TOKEN, this)
        presenter = CreditCardTransactionDetailsPresenter(this, vb)
        adapter = CreditCardTransactionDetailsAdapter(presenter)
        return AbstractClassProperties(presenter, adapter, "Your Credit Card Details", appStyleRes = R.style.AppTheme)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Back Button on Nav Bar. If bar null, no back button. User can use physical back.
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Title Setup
        titleText = findViewById(R.id.centerTitle)
        titleText.text = getString(R.string.credit_card_title)
        titleText.textSize = 40f

        // Preview ListView Setup
        previewPresenter = CardPreviewPresenter(this, vb)
        previewAdapter = CardPreviewAdapter(previewPresenter)
        previewPresenter.onViewReady()
        previewAdapter.buildRows()

        cardPreviewListView = findViewById(R.id.creditCardPageCard)
        cardPreviewListView.apply {
            layoutManager = LinearLayoutManager(this@CreditCardActivity)
            itemAnimator = DefaultItemAnimator()
            adapter = previewAdapter
        }
        // Custom scroll and background behavior
        cardPreviewListView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        cardPreviewListView.setBackgroundResource(R.drawable.td_couch)
        cardPreviewListView.setPadding(getPixelsGivenDP(80f), getPixelsGivenDP(15f), 0, 0)
        cardPreviewListView.addOnScrollListener(createRVScrollListener())
        cardPreviewListView.layoutParams.height = getPixelsGivenDP(200f)

        val snapHelper: SnapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(cardPreviewListView)
    }

    private fun createRVScrollListener(): RecyclerView.OnScrollListener {
        return object: RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy > 0) {
                    cardPreviewListView.setPadding(getPixelsGivenDP(0f), getPixelsGivenDP(15f), 0, 0)
                    return
                }
                cardPreviewListView.setPadding(getPixelsGivenDP(80f), getPixelsGivenDP(15f), 0, 0)
            }
        }
    }

    private fun getPixelsGivenDP(dp: Float): Int {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, resources.displayMetrics).toInt()
    }

    // AbstractRecyclerViewActivity
    override fun getLayoutId(): Int {
        return R.layout.credit_card_activity
    }

    override fun getRecyclerViewId(): Int {
        return R.id.creditCardBottom
    }

    // AppCompatActivity
    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    // CreditCardPreviewView
    override fun reloadDetailsCard() {
        previewAdapter.reload()
    }

    override fun getCreditCardAccounts(): ArrayList<VirtualBankCreditCardAccount>? {
        return VirtualBankInformationHolder.creditCardAccounts
    }

    override fun storeResponse(accounts: ArrayList<VirtualBankCreditCardAccount>?) {
        VirtualBankInformationHolder.creditCardAccounts = accounts
    }

    override fun makeCreditCardAccountsCall(vb: VirtualBank) {
        vb.getCustomerCreditCardAccounts(this, Constants.SELECTED_PROFILE.profile.id,
                previewPresenter.getCreditAccountsClosure())
    }

    override fun previewCardLoaded(accounts: ArrayList<VirtualBankCreditCardAccount>?) {
        presenter.selectedCard = (accounts ?: return).first()
        presenter.onCardChanged(presenter.selectedCard)
    }

    override fun cardChanged(account: VirtualBankCreditCardAccount) {
        if (presenter.selectedCard == account) return else presenter.onCardChanged(account)
    }

    // CreditCardTransactionDetailsView
    override fun reloadTransactions() {
        adapter.reload()
    }

    override fun getTransactions(account: VirtualBankCreditCardAccount): ArrayList<VirtualBankTransaction>? {
        return VirtualBankInformationHolder.creditCardTransactions[account]
    }

    override fun storeTransactions(account: VirtualBankCreditCardAccount, transactions: ArrayList<VirtualBankTransaction>?) {
        VirtualBankInformationHolder.creditCardTransactions[account] = transactions
    }

    override fun makeTransactionsCall(vb: VirtualBank, account: VirtualBankCreditCardAccount) {
        vb.getTransactions(this, account.id, presenter.getTransactionsRequestClosure())
    }
}