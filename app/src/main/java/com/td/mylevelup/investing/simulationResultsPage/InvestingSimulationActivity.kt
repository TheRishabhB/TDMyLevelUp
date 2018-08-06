package com.td.mylevelup.investing.simulationResultsPage

import android.os.Bundle
import com.ngam.rvabstractions.screens.AbstractActivity
import com.ngam.rvabstractions.screens.AbstractClassProperties
import com.td.mylevelup.R
import okhttp3.OkHttpClient

class InvestingSimulationActivity: AbstractActivity<InvestingSimulationPresenter, InvestingSimulationAdapter>(),
        InvestingSimulationView {
    // Constant to allow for passing symbol to activity
    companion object {
        val SYMBOL_KEY: String = "SYMBOL_TO_SEARCH"
    }

    private lateinit var selectedSymbol: String
    private lateinit var client: OkHttpClient

    override fun setProperties(): AbstractClassProperties<InvestingSimulationPresenter, InvestingSimulationAdapter> {
        presenter = InvestingSimulationPresenter(selectedSymbol, this, client)
        adapter = InvestingSimulationAdapter()
        return AbstractClassProperties(presenter, adapter, "Investing Simulation Results",
                appStyleRes = R.style.AppTheme)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        // Get symbol before setProperties is called.
        val bundle: Bundle = intent.extras
        selectedSymbol = bundle.getString(SYMBOL_KEY)
        client = OkHttpClient()

        super.onCreate(savedInstanceState)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    // AppCompatActivity
    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    // View
    override fun reloadResults() {
        reload()
    }
}