package com.td.mylevelup.models.alphaVantage

import com.google.gson.annotations.SerializedName

data class SymbolDetailsResponse(@SerializedName("Meta Data") val metaData: MetaData,
                                 @SerializedName("Time Series (Daily)") val timeSeries: TimeSeries)