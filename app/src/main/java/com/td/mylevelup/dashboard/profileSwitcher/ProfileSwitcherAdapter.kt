package com.td.mylevelup.dashboard.profileSwitcher

import com.ngam.rvabstractions.multiCard.AbstractMultiCardPagerAdapter
import com.td.mylevelup.Constants
import com.td.mylevelup.components.profileSwitcherCard.ProfileSwitcherCardBinder

class ProfileSwitcherAdapter: AbstractMultiCardPagerAdapter() {
    override fun buildRows() {
        listItems.clear()
        for (customer in Constants.CUSTOMER_LIST) {
            listItems.add(ProfileSwitcherCardBinder(customer.profile))
        }
    }
}