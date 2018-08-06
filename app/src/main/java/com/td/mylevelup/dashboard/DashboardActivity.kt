package com.td.mylevelup.dashboard

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.ngam.rvabstractions.general.OnInputReceived
import com.td.mylevelup.Constants
import com.td.mylevelup.R
import com.td.mylevelup.VirtualBankInformationHolder
import com.td.mylevelup.dashboard.accountsCard.AccountsCard
import com.td.mylevelup.dashboard.creditCardCard.CreditCardCard
import com.td.mylevelup.dashboard.profileSwitcher.ProfileSwitcherView
import com.td.mylevelup.models.CustomerProfiles

class DashboardActivity : AppCompatActivity() {

    private lateinit var accountsCardView: AccountsCard
    private lateinit var creditCardCard: CreditCardCard

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        supportActionBar?.hide()

        val switcher: ProfileSwitcherView = findViewById(R.id.profileSwitcherView)
        switcher.attachScrollListener(createScrollListener())

        accountsCardView = findViewById(R.id.accountsCard)
        creditCardCard = findViewById(R.id.creditCardCard)
    }

    private fun createScrollListener(): OnInputReceived<CustomerProfiles> {
        return object: OnInputReceived<CustomerProfiles> {
            override fun onInputReceived(input: CustomerProfiles) {
                Constants.SELECTED_PROFILE = input
                // TODO: Proper Caching for multiple users. At the moment only caches for one user.
                VirtualBankInformationHolder.clearAllValues()
                accountsCardView.dataChanged()
                creditCardCard.dataChanged()
            }
        }
    }
}