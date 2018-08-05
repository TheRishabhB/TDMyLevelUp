package com.td.mylevelup.dashboard.profileSwitcher

import android.content.Context
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import com.ngam.rvabstractions.general.OnInputReceived
import com.ngam.rvabstractions.multiCard.AbstractMultiCardPagerView
import com.ngam.rvabstractions.multiCard.MultiCardDataSource
import com.td.mylevelup.Constants
import com.td.mylevelup.models.CustomerProfiles

class ProfileSwitcherView(context: Context, attrSet: AttributeSet?, defStyleAttr: Int):
        AbstractMultiCardPagerView<ProfileSwitcherAdapter>(context, attrSet, defStyleAttr) {
    // Constructors for LinearLayout
    constructor(context: Context, attrSet: AttributeSet?) : this(context, attrSet, 0)
    constructor(context: Context) : this(context, null)

    private lateinit var scrollListener: OnInputReceived<CustomerProfiles>

    init {
        pager.addOnPageChangeListener(onProfileChanged())
    }

    override fun setDataSource(): MultiCardDataSource<ProfileSwitcherAdapter> {
        adapter = ProfileSwitcherAdapter()
        return MultiCardDataSource(adapter, pagerHeight = 240,
                pagerLeftPadding = 75, pagerRightPadding = 175)
    }

    fun attachScrollListener(scrollListener: OnInputReceived<CustomerProfiles>) {
        this.scrollListener = scrollListener
    }

    private fun onProfileChanged(): ViewPager.OnPageChangeListener {
        return object: ViewPager.SimpleOnPageChangeListener() {
            override fun onPageSelected(position: Int) {
                scrollListener.onInputReceived(Constants.CUSTOMER_LIST[position])
            }
        }
    }
}