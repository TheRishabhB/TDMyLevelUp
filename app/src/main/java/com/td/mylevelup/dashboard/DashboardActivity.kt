package com.td.mylevelup.dashboard

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.view.View
import android.widget.TextView
import com.android.volley.VolleyError
import com.facebook.shimmer.ShimmerFrameLayout
import com.ngam.rvabstractions.general.OnInputReceived
import com.td.mylevelup.Constants
import com.td.mylevelup.R
import com.td.mylevelup.VirtualBankInformationHolder
import com.td.mylevelup.dashboard.accountsCard.AccountsCard
import com.td.mylevelup.dashboard.creditCardCard.CreditCardCard
import com.td.mylevelup.dashboard.creditCardCard.CreditCardCardView
import com.td.mylevelup.dashboard.profileSwitcher.ProfileSwitcherView
import com.td.mylevelup.models.CustomerProfiles
import com.td.mylevelup.models.Profile
import com.td.virtualbank.*

class DashboardActivity : AppCompatActivity() {

    private lateinit var accountsCardView: AccountsCard
    private lateinit var creditCardCard: CreditCardCard

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        val switcher: ProfileSwitcherView = findViewById(R.id.profileSwitcherView)
        switcher.attachScrollListener(createScrollListener())

        accountsCardView = findViewById(R.id.accountsCard)
        creditCardCard = findViewById(R.id.creditCardCard)

        supportActionBar?.hide()
    }

    private fun createScrollListener(): OnInputReceived<CustomerProfiles> {
        return object: OnInputReceived<CustomerProfiles> {
            override fun onInputReceived(input: CustomerProfiles) {
                Constants.SELECTED_PROFILE = input
                // TODO: Proper Cache
                VirtualBankInformationHolder.clearAllValues()
                accountsCardView.dataChanged()
                creditCardCard.dataChanged()
            }
        }
    }
}