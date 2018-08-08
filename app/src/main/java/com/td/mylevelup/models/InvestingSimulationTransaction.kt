package com.td.mylevelup.models

data class InvestingSimulationTransaction(val stockSymbol: String,
                                          val expenseName: String,
                                          val amount: Double,
                                          val price: Double,
                                          val amountHeld: Double,
                                          val date: String)