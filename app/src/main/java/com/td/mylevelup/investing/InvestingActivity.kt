package com.td.mylevelup.investing

import android.content.Context
import android.os.Bundle
import android.support.v7.widget.SearchView
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ngam.rvabstractions.screens.AbstractActivity
import com.ngam.rvabstractions.screens.AbstractClassProperties
import com.td.mylevelup.R
import com.td.mylevelup.models.SearchSymbolModel
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class InvestingActivity: AbstractActivity<InvestingPagePresenter, InvestingPageAdapter>(), InvestingPageView {

    private lateinit var searchBar: SearchView
    private lateinit var client: OkHttpClient

    override fun setProperties(): AbstractClassProperties<InvestingPagePresenter, InvestingPageAdapter> {
        client = OkHttpClient()
        presenter = InvestingPagePresenter(this, client)
        adapter = InvestingPageAdapter(presenter)
        return AbstractClassProperties(presenter, adapter, "Search for a Symbol")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        searchBar = findViewById(R.id.symbolSearchBar)
        searchBar.setIconifiedByDefault(false)

        val searchBarEditText: EditText = findViewById(android.support.v7.appcompat.R.id.search_src_text)
        searchBarEditText.setTextColor(getColor(R.color.text_color))

        searchBar.setOnClickListener {
            // Dismiss keyboard.
            val inputManager: InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputManager.hideSoftInputFromWindow(searchBarEditText.windowToken, 0)
        }

        searchBar.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String?): Boolean {
                presenter.setSearchQueryAndSearch(newText ?: "")
                return true
            }
            override fun onQueryTextSubmit(query: String?): Boolean {
                // Not needed but required to override
                return true
            }
        })
    }

    // AppCompatActivity
    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    // AbstractActivity
    override fun getLayoutId(): Int {
        return R.layout.investing_search_activity
    }

    // View
    override fun reloadSymbolsList() {
        reload()
    }
}