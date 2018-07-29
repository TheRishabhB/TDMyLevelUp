package com.td.mylevelup.dashboard.accountsCard

import com.td.virtualbank.VirtualBank
import com.td.virtualbank.VirtualBankBankAccount
import java.util.ArrayList

interface AccountsCardView {
    fun reloadCard()
    fun storeResponse(accounts: ArrayList<VirtualBankBankAccount>?)
    fun getBankAccounts(): ArrayList<VirtualBankBankAccount>?
    fun makeBankAccountsCall(vb: VirtualBank)
    fun launchAccountsDetailsPage()
}