package com.td.mylevelup.mortgage.mortgageResultsPage

import android.os.Bundle
import android.view.View
import android.widget.RelativeLayout
import android.widget.TextView
import com.android.volley.VolleyError
import com.ngam.rvabstractions.screens.AbstractActivity
import com.ngam.rvabstractions.screens.AbstractClassProperties
import com.td.mylevelup.Constants.Companion.AUTH_TOKEN
import com.td.mylevelup.R
import com.td.virtualbank.VirtualBank
import com.td.virtualbank.VirtualBankGetCustomerTransactionsRequest
import com.td.virtualbank.VirtualBankTransaction
import java.math.BigDecimal
import java.math.RoundingMode
import java.util.*
import kotlin.concurrent.schedule

class MortgagePaymentResultActivity : AbstractActivity<MortgagePaymentResultPresenter, MortgagePaymentResultAdapter>(), MortgagePaymentResultView {

    private lateinit var monthlyPayments: String
    private lateinit var principal: String
    private lateinit var interest: String
    private lateinit var period: String
    private var sum: BigDecimal = BigDecimal.ZERO

    private val vb = VirtualBank.getBank(AUTH_TOKEN, this)

    private lateinit var monthlyPaymentsTextView: TextView
    private lateinit var eligibilityTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mortgage_payment_result)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        var isCustomerEligible: Boolean

        monthlyPayments = intent.getStringExtra(getString(R.string.monthly_payments))
        principal = intent.getStringExtra(getString(R.string.principal))
        interest = intent.getStringExtra(getString(R.string.rate))
        period = intent.getStringExtra(getString(R.string.period))

        monthlyPaymentsTextView = findViewById(R.id.monthly_payments)
        monthlyPaymentsTextView.apply {
            this.text = getString(R.string.mortgage_payment_result, monthlyPayments)
        }

        eligibilityTextView = findViewById(R.id.eligibility_text_view)

        vb.getCustomerTransactions(this, "1f9e0890-77b5-44ae-a987-03a0a6a1029a_e2ba9727-a181-48f6-a1bc-0abf5ce173a2",
                object : VirtualBankGetCustomerTransactionsRequest() {
                    override fun onSuccess(p0: ArrayList<VirtualBankTransaction>?) {
                        p0!!.forEach {
                            sum = sum.add(BigDecimal(it.currencyAmount))
                        }
                        val average = sum.divide(BigDecimal(p0.size), 2, RoundingMode.HALF_EVEN)
                        val greaterIncome = BigDecimal(monthlyPayments).divide(BigDecimal(16),
                                2, RoundingMode.HALF_EVEN)

                        isCustomerEligible = presenter.isCustomerEligible(average, greaterIncome)

                        eligibilityTextView.apply {
                            this.text = getTextView(isCustomerEligible)
                        }

                        // Delay by one second since the server response is way too fast it makes the data look
                        // Stubbed.
                        Timer().schedule(1000) {
                            runOnUiThread {
                                (findViewById<RelativeLayout>(R.id.loadingPanel)).visibility = View.GONE
                            }
                        }
                    }

                    override fun onError(p0: VolleyError?) {
                    }
                })
    }

    override fun setProperties(): AbstractClassProperties<MortgagePaymentResultPresenter, MortgagePaymentResultAdapter> {
        presenter = MortgagePaymentResultPresenter(this)
        adapter = MortgagePaymentResultAdapter(presenter)

        return AbstractClassProperties(presenter, adapter,
                getString(R.string.mortgage_payment_results_title)
                , false, appStyleRes = R.style.AppTheme)
    }

    override fun getTextView(isEligible: Boolean): String {
        if (isEligible) {
            return getString(R.string.mortgage_eligible)
        }
        return getString(R.string.mortgage_not_eligible)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}