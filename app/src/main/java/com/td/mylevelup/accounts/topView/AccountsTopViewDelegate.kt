package com.td.mylevelup.accounts.topView

import com.td.virtualbank.VirtualBankBankAccount
import java.util.ArrayList

interface AccountsTopViewDelegate {
    fun topViewLoaded(accounts: ArrayList<VirtualBankBankAccount>)
    fun onAccountSelected(account: VirtualBankBankAccount)
}