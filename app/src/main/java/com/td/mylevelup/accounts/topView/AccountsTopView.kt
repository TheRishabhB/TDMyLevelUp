package com.td.mylevelup.accounts.topView

import com.td.virtualbank.VirtualBank
import com.td.virtualbank.VirtualBankBankAccount
import java.util.ArrayList

interface AccountsTopView {
    fun reloadTop()
    fun notifyTopLoaded(accounts: ArrayList<VirtualBankBankAccount>)
    fun storeBankAccountsResponse(accounts: ArrayList<VirtualBankBankAccount>?)
    fun getBankAccounts(): ArrayList<VirtualBankBankAccount>?
    fun makeBankAccountsCall(vb: VirtualBank)
}