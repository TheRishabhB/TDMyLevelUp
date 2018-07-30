package com.td.mylevelup.creditCard.cardPreview

import com.td.virtualbank.VirtualBank
import com.td.virtualbank.VirtualBankCreditCardAccount
import java.util.ArrayList

interface CreditCardPreviewView {
    fun reloadDetailsCard()
    fun storeResponse(accounts: ArrayList<VirtualBankCreditCardAccount>?)
    fun getCreditCardAccounts(): ArrayList<VirtualBankCreditCardAccount>?
    fun makeCreditCardAccountsCall(vb: VirtualBank)
    fun previewCardLoaded(accounts: ArrayList<VirtualBankCreditCardAccount>?)
    fun cardChanged(account: VirtualBankCreditCardAccount)
}