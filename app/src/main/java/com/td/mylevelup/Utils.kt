package com.td.mylevelup

import android.app.Activity
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.td.mylevelup.Constants.Companion.FREQUENCY_BI_WEEKLY
import com.td.mylevelup.Constants.Companion.FREQUENCY_WEEKLY
import java.math.BigDecimal
import java.math.RoundingMode

class Utils private constructor() {

    companion object {
        fun calculatePayment(baseAmount: String,
                             interest: String,
                             period: Int,
                             freq: String): BigDecimal {
            val principal = BigDecimal(baseAmount)
            val interestRate = getInterestRateGivenFrequency(interest, freq).divide(BigDecimal(100))
            val numOfPayments = getNumberOfPayments(period, freq)

            return principal.multiply(interestRate.multiply((interestRate.add(BigDecimal.ONE))
                    .pow(numOfPayments)))
                    .divide((interestRate.add(BigDecimal.ONE)).pow(numOfPayments))
                    .subtract(BigDecimal.ONE)
                    .setScale(2, RoundingMode.HALF_EVEN).multiply(getMultiplier(freq))
        }

        private fun getInterestRateGivenFrequency(interest: String, freq: String): BigDecimal {
            if (freq == FREQUENCY_WEEKLY) {
                return BigDecimal(interest).divide(BigDecimal(Constants.WEEKS_IN_YEAR), 2, RoundingMode.HALF_EVEN)
            } else if (freq == FREQUENCY_BI_WEEKLY) {
                return BigDecimal(interest).divide((BigDecimal(Constants.WEEKS_IN_YEAR)).divide(BigDecimal(2)), 2, RoundingMode.HALF_EVEN)
            }
            return BigDecimal(interest).divide(BigDecimal(Constants.MONTHS_IN_YEAR), 2, RoundingMode.HALF_EVEN)
        }

        private fun getNumberOfPayments(period: Int, freq: String): Int {
            if (freq == FREQUENCY_WEEKLY) {
                return BigDecimal(period).multiply(BigDecimal(Constants.WEEKS_IN_YEAR)).toInt()
            } else if (freq == FREQUENCY_BI_WEEKLY) {
                return BigDecimal(period).multiply(BigDecimal(Constants.WEEKS_IN_YEAR)).divide(BigDecimal(2)).toInt()
            }
            return BigDecimal(period).multiply(BigDecimal(Constants.MONTHS_IN_YEAR)).toInt()
        }

        private fun getMultiplier(freq: String): BigDecimal {
            if (freq == FREQUENCY_WEEKLY) {
                return BigDecimal(4)
            } else if (freq == FREQUENCY_BI_WEEKLY) {
                return BigDecimal(2)
            }
            return BigDecimal.ONE
        }

        fun hideKeyboard(activity: Activity) {
            val imm = activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            //Find the currently focused view, so we can grab the correct window token from it.
            var view = activity.currentFocus
            //If no view currently has focus, create a new one, just so we can grab a window token from it
            if (view == null) {
                view = View(activity)
            }
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }
}