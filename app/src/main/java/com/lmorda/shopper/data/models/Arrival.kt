package com.lmorda.shopper.data.models

data class Arrival (
    val orderNum: Int,
    val status: String,
    val arrivalFirst: String,
    val arrivalSecond: String,
    val statusDetails: String,
    val storeName: String
)
