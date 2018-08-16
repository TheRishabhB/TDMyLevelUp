package com.td.mylevelup.dashboard.mortgageCard

import android.content.Context
import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.PagerSnapHelper
import android.util.AttributeSet
import com.ngam.rvabstractions.singleCard.CardDataSource
import com.ngam.rvabstractions.singleCard.GenericCardView
import com.td.mylevelup.R.drawable.mortgage_icon_small
import com.td.mylevelup.mortgage.mortgageCalculatorPage.MortgageActivity


class MortgageCard(context: Context, attrSet: AttributeSet?, defStyleAttr: Int):
        GenericCardView<MortgageCardPresenter, MortgageCardAdapter>(context, attrSet, defStyleAttr), MortgageCardView {

    constructor(context: Context, attrSet: AttributeSet?): this(context, attrSet, 0)
    constructor(context: Context): this(context, null)

    init {
        cardListView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(cardListView)
    }

    override fun setDataSource(): CardDataSource<MortgageCardPresenter, MortgageCardAdapter> {
        presenter = MortgageCardPresenter(this)
        adapter = MortgageCardAdapter(context, presenter)
        return CardDataSource(presenter, adapter, "Mortgage:",
                mortgage_icon_small, OnClickListener { launchMortgageActivity() })
    }

    override fun launchMortgageActivity() {
        val intent = Intent(context, MortgageActivity::class.java)
        context.startActivity(intent)
    }
}