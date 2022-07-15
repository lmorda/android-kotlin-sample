package com.lmorda.shopper.data.models

data class StoreItems(
    val items: List<FoodItem>,
    val nextPage: Int?
)