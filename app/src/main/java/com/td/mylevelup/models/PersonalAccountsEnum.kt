package com.td.mylevelup.models

enum class PersonalAccountsEnum {
    ALL_INCLUSIVE_BANKING,
    UNLIMITED_CHEQUING,
    EVERYDAY_CHEQUING,
    MINIMUM_CHEQUING;

    fun getRecommendedText(): String  = when (this) {
        ALL_INCLUSIVE_BANKING -> "Because your balance is over $5000, you can take advantage of the rebate and get the "
        UNLIMITED_CHEQUING -> "You seem to have over $4000 in your balances. Maybe you would be interested in an "
        EVERYDAY_CHEQUING -> "You seem to have over $3000 in your balances. Maybe you would be interested in an "
        MINIMUM_CHEQUING -> "After analyzing your transactions, maybe you would be interested in a  "
    }

    fun getAccountName(): String = when (this) {
        ALL_INCLUSIVE_BANKING -> "All Inclusive Banking Account!"
        UNLIMITED_CHEQUING -> "Unlimited Chequing Account!"
        EVERYDAY_CHEQUING -> "Everyday Chequing Account!"
        MINIMUM_CHEQUING -> "Minimum Chequing Account!"
    }

    fun getAccountURL(): String = when (this) {
        ALL_INCLUSIVE_BANKING -> "https://www.td.com/ca/en/personal-banking/products/bank-accounts/chequing-accounts/all-inclusive-banking-plan/"
        UNLIMITED_CHEQUING -> "https://www.td.com/ca/en/personal-banking/products/bank-accounts/chequing-accounts/unlimited-chequing-account/"
        EVERYDAY_CHEQUING -> "https://www.td.com/ca/en/personal-banking/products/bank-accounts/savings-accounts/every-day-savings-account/"
        MINIMUM_CHEQUING -> "https://www.td.com/ca/en/personal-banking/products/bank-accounts/chequing-accounts/minimum-chequing-account/"
    }
}