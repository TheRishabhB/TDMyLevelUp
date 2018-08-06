package com.td.mylevelup.investing

import android.os.Bundle
import android.support.v7.widget.SearchView
import android.widget.EditText
import com.ngam.rvabstractions.screens.AbstractActivity
import com.ngam.rvabstractions.screens.AbstractClassProperties
import com.td.mylevelup.R

class InvestingActivity: AbstractActivity<InvestingPagePresenter, InvestingPageAdapter>() {

    private lateinit var searchBar: SearchView

    override fun setProperties(): AbstractClassProperties<InvestingPagePresenter, InvestingPageAdapter> {
        presenter = InvestingPagePresenter()
        adapter = InvestingPageAdapter()
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
            val x = 5
        }

        searchBar.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
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
}