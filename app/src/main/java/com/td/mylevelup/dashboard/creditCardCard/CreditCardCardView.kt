package com.td.mylevelup.dashboard.creditCardCard

import com.td.virtualbank.VirtualBank
import com.td.virtualbank.VirtualBankCreditCardAccount
import java.util.ArrayList

interface CreditCardCardView {
    fun reloadCard()
    fun storeResponse(accounts: ArrayList<VirtualBankCreditCardAccount>?)
    fun getCreditCardAccounts(): ArrayList<VirtualBankCreditCardAccount>?
    fun makeCreditCardAccountsCall(vb: VirtualBank)
    fun launchCreditCardDetailsPage()
}