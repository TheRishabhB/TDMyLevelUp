package com.td.mylevelup

import com.td.virtualbank.VirtualBankBankAccount
import com.td.virtualbank.VirtualBankCreditCardAccount
import com.td.virtualbank.VirtualBankCustomer
import java.util.ArrayList

/**
 * Note: Normally using singletons to hold network responses as a cache is a not a good practice.
 * However this application is a proof of concept, so this is being used instead of properly caching responses.
 */
object VirtualBankInformationHolder {
    var customer: VirtualBankCustomer? = null
    var bankAccounts: ArrayList<VirtualBankBankAccount>? = null
    var creditCardAccounts: ArrayList<VirtualBankCreditCardAccount>? = null

    fun clearAllValues() {
        customer = null
        bankAccounts = null
        creditCardAccounts = null
    }
}