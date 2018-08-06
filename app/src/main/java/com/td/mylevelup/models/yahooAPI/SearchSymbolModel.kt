package com.td.mylevelup.models.yahooAPI

import com.google.gson.annotations.SerializedName

data class SearchSymbolModel(@SerializedName("symbol") val symbol: String,
                             @SerializedName("name") val name: String,
                             @SerializedName("exch") val exch: String,
                             @SerializedName("type") val type: String,
                             @SerializedName("exchDisp") val exchDisp: String,
                             @SerializedName("typeDisp") val typeDisp: String)