package com.lmorda.shopper.data.models

data class FoodItem(
    val id: Int,
    val price: Double,
    val name: String,
    val imageRes: Int,
    val category: FoodCategory,
    var inCart: Boolean = false,
)