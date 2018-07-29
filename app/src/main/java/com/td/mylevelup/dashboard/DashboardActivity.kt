package com.td.mylevelup.dashboard

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.android.volley.VolleyError
import com.facebook.shimmer.ShimmerFrameLayout
import com.td.mylevelup.Constants
import com.td.mylevelup.R
import com.td.mylevelup.VirtualBankInformationHolder
import com.td.mylevelup.dashboard.accountsCard.AccountsCard
import com.td.virtualbank.*

class DashboardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        // Make Customer Call to get Name
        val vb: VirtualBank = VirtualBank.getBank(Constants.AUTH_TOKEN)
        if (VirtualBankInformationHolder.customer == null) {
            vb.getCustomer(this, Constants.IVANA_EASTOM_STUDENT_ID, createCustomerClosure())
            return
        }
        handleCustomerResponse(VirtualBankInformationHolder.customer)
    }

    private fun createCustomerClosure(): VirtualBankGetCustomerRequest {
        return object: VirtualBankGetCustomerRequest() {
            override fun onSuccess(p0: VirtualBankCustomer?) {
                handleCustomerResponse(p0)
            }

            override fun onError(p0: VolleyError?) {
                handleCustomerResponse(null)
            }
        }
    }

    private fun handleCustomerResponse(customer: VirtualBankCustomer?) {
        val userNameText: TextView = findViewById(R.id.userNameText)
        val shimmerText: ShimmerFrameLayout = findViewById(R.id.loadingShimmerFrameLayout)
        if (customer == null) {
            shimmerText.visibility = View.GONE
            userNameText.visibility = View.VISIBLE
            userNameText.text = getString(R.string.welcome)
            return
        }

        shimmerText.visibility = View.GONE
        userNameText.visibility = View.VISIBLE
        userNameText.text = String.format(getString(R.string.name_format), customer.givenName, customer.surname)
        VirtualBankInformationHolder.customer = customer
    }
}