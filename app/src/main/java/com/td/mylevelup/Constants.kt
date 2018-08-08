package com.td.mylevelup

import com.td.mylevelup.models.CustomerProfiles

class Constants private constructor() {
    companion object {
        const val AUTH_TOKEN: String = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJDQlAiLCJ0ZWFtX2lkIjoiMjgxMzgzOCIsImV4cCI6OTIyMzM3MjAzNjg1NDc3NSwiYXBwX2lkIjoiMWY5ZTA4OTAtNzdiNS00NGFlLWE5ODctMDNhMGE2YTEwMjlhIn0.FEdf6MQ1XLVFaaV3V6Ocf3aAcGXksANva2K_YTUz2EA"
//        const val AUTH_TOKEN: String = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJDQlAiLCJ0ZWFtX2lkIjoidGVzdFRlYW1JZCIsImV4cCI6OTIyMzM3MjAzNjg1NDc3NSwiYXBwX2lkIjoidGVzdEFwcElkIn0.o53TISzOUq0T1dc96IiEc8rreexYLCrdI3HunhtPvJ4"
        val ALPHA_VANTAGE_AUTH_TOKEN: String = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJDQlAiLCJ0ZWFtX2lkIjoiMjgxMzgzOCIsImV4cCI6OTIyMzM3MjAzNjg1NDc3NSwiYXBwX2lkIjoiMWY5ZTA4OTAtNzdiNS00NGFlLWE5ODctMDNhMGE2YTEwMjlhIn0.FEdf6MQ1XLVFaaV3V6Ocf3aAcGXksANva2K_YTUz2EA"
        val ALPHA_VANTAGE_API_KEY: String = "TS988KUY986K34W5"

        val CUSTOMER_LIST: ArrayList<CustomerProfiles> = ArrayList()

        // Note: Below is not a constant but due to lack of time I am placing it here.
        var SELECTED_PROFILE: CustomerProfiles = CustomerProfiles.IVANA_EASTOM

        init {
            CUSTOMER_LIST.add(CustomerProfiles.IVANA_EASTOM)
            CUSTOMER_LIST.add(CustomerProfiles.GALEN_NEVIUS)
            CUSTOMER_LIST.add(CustomerProfiles.FRANK_NERLINSKI)
            CUSTOMER_LIST.add(CustomerProfiles.TAYLAH_BASORA)
            CUSTOMER_LIST.add(CustomerProfiles.YUETTE_MCCALLION)
        }


    }
}