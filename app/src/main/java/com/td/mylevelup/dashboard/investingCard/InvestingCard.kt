package com.td.mylevelup.dashboard.investingCard

import android.content.Context
import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.PagerSnapHelper
import android.util.AttributeSet
import com.ngam.rvabstractions.singleCard.CardDataSource
import com.ngam.rvabstractions.singleCard.GenericCardView
import com.td.mylevelup.R
import com.td.mylevelup.investing.InvestingActivity

class InvestingCard(context: Context, attrSet: AttributeSet?, defStyleAttr: Int):
        GenericCardView<InvestingCardPresenter, InvestingCardAdapter>(context, attrSet, defStyleAttr), InvestingCardView {
    // Constructors for LinearLayout
    constructor(context: Context, attrSet: AttributeSet?): this(context, attrSet, 0)
    constructor(context: Context): this(context, null)

    init {
        cardListView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(cardListView)
    }

    override fun setDataSource(): CardDataSource<InvestingCardPresenter, InvestingCardAdapter> {
        presenter = InvestingCardPresenter(this)
        adapter = InvestingCardAdapter(context, presenter)
        return CardDataSource(presenter, adapter, "Investing:",
                R.drawable.investing_icon_small, OnClickListener { launchSearchStocksScreen() })
    }

    override fun launchSearchStocksScreen() {
        val intent = Intent(context, InvestingActivity::class.java)
        context.startActivity(intent)
    }
}