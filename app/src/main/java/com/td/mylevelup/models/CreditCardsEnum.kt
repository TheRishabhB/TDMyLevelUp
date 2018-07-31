package com.td.mylevelup.models

import android.support.annotation.DrawableRes
import com.td.mylevelup.R

enum class CreditCardsEnum {
    AEROPLAN_VISA_INFINITE,
    AEROPLAN_VISA_PRIVILEGE,
    AEROPLAN_VISA_PLATINUM,
    CASH_BACK_VISA_INFINITE,
    CASH_BACK_VISA,
    EMERALD_FLEX_RATE,
    FIRST_CLASS_TRAVEL_VISA_INFINITE,
    PLATINUM_TRAVEL,
    REWARDS,
    US_DOLLAR;

    fun getCardName(): String {
        when (this) {
            AEROPLAN_VISA_INFINITE -> return "Aeroplan Visa Infinite Card"
            AEROPLAN_VISA_PRIVILEGE -> return "Aeroplan Visa Infinite Privilege Card"
            AEROPLAN_VISA_PLATINUM -> return "Aeroplan Visa Platinum Card"
            CASH_BACK_VISA_INFINITE -> return "Cash Back Visa Infinite Card"
            CASH_BACK_VISA -> return "Cash Back Visa Card"
            EMERALD_FLEX_RATE -> return "Emerald Flex Rate Visa Card"
            FIRST_CLASS_TRAVEL_VISA_INFINITE -> return "First Class Travel Visa Infinite Card"
            PLATINUM_TRAVEL -> return "Platinum Travel Visa Card"
            REWARDS -> return "Rewards Visa Card"
            US_DOLLAR -> return "Us Dollar Visa Card"
        }
    }

    @DrawableRes
    fun getCardImage(): Int {
        when (this) {
            AEROPLAN_VISA_INFINITE -> return R.drawable.td_aeroplan_visa_infinite_card
            AEROPLAN_VISA_PRIVILEGE -> return R.drawable.td_aeroplan_visa_infinite_privilege_card
            AEROPLAN_VISA_PLATINUM -> return R.drawable.td_aeroplan_visa_platinum_card
            CASH_BACK_VISA_INFINITE -> return R.drawable.td_cash_back_visa_infinite_card
            CASH_BACK_VISA -> return R.drawable.td_cash_back_visa_card
            EMERALD_FLEX_RATE -> return R.drawable.td_emerald_flex_rate_visa_card
            FIRST_CLASS_TRAVEL_VISA_INFINITE -> return R.drawable.td_first_class_travel_visa_infinite_card
            PLATINUM_TRAVEL -> return R.drawable.td_platinum_travel_visa_card
            REWARDS -> return R.drawable.td_rewards_visa_card
            US_DOLLAR -> return R.drawable.td_us_dollar_visa_card
        }
    }

    fun getUrl(): String {
        when (this) {
            AEROPLAN_VISA_INFINITE -> return "https://www.td.com/ca/en/personal-banking/products/credit-cards/aeroplan/aeroplan-visa-infinite-card/"
            AEROPLAN_VISA_PRIVILEGE -> return "https://www.td.com/ca/en/personal-banking/products/credit-cards/aeroplan/aeroplan-visa-infinite-privilege-card/"
            AEROPLAN_VISA_PLATINUM -> return "https://www.td.com/ca/en/personal-banking/products/credit-cards/aeroplan/aeroplan-visa-platinum-card/"
            CASH_BACK_VISA_INFINITE -> return "https://www.td.com/ca/en/personal-banking/products/credit-cards/cash-back/cash-back-visa-infinite-card/"
            CASH_BACK_VISA -> return "https://www.td.com/ca/en/personal-banking/products/credit-cards/cash-back/cash-back-visa-card/"
            EMERALD_FLEX_RATE -> return "https://www.td.com/ca/en/personal-banking/products/credit-cards/low-rate/emerald-flex-rate-visa-card/"
            FIRST_CLASS_TRAVEL_VISA_INFINITE -> return "https://www.td.com/ca/en/personal-banking/products/credit-cards/travel-rewards/first-class-travel-visa-infinite-card/"
            PLATINUM_TRAVEL -> return "https://www.td.com/ca/en/personal-banking/products/credit-cards/travel-rewards/platinum-travel-visa-card/"
            REWARDS -> return "https://www.td.com/ca/en/personal-banking/products/credit-cards/travel-rewards/rewards-visa-card/"
            US_DOLLAR -> return "https://www.td.com/ca/en/personal-banking/products/credit-cards/us-dollar/us-dollar-visa-card/"
        }
    }
}