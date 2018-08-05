package com.td.mylevelup

import com.td.mylevelup.models.CustomerProfiles

class Constants private constructor() {
    companion object {
        const val AUTH_TOKEN: String = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJDQlAiLCJ0ZWFtX2lkIjoiMjgxMzgzOCIsImV4cCI6OTIyMzM3MjAzNjg1NDc3NSwiYXBwX2lkIjoiMWY5ZTA4OTAtNzdiNS00NGFlLWE5ODctMDNhMGE2YTEwMjlhIn0.FEdf6MQ1XLVFaaV3V6Ocf3aAcGXksANva2K_YTUz2EA"
        var SELECTED_PROFILE: CustomerProfiles = CustomerProfiles.IVANA_EASTOM
        val CUSTOMER_LIST: ArrayList<CustomerProfiles> = ArrayList()
        val CACHED_DATA: HashMap<CustomerProfiles, VirtualBankInformationHolder> = HashMap()

        init {
            CUSTOMER_LIST.add(CustomerProfiles.IVANA_EASTOM)
            CUSTOMER_LIST.add(CustomerProfiles.GALEN_NEVIUS)
            CUSTOMER_LIST.add(CustomerProfiles.FRANK_NERLINSKI)
            CUSTOMER_LIST.add(CustomerProfiles.TAYLAH_BASORA)
            CUSTOMER_LIST.add(CustomerProfiles.YUETTE_MCCALLION)
        }


    }
}