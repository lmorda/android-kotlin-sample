package com.lmorda.shopper.details

import com.lmorda.shopper.data.models.FoodItem

sealed class DetailsViewState {

    object Loading : DetailsViewState()

    data class Error(val error: Throwable) : DetailsViewState()

    data class DetailsItem(val foodItem: FoodItem?) : DetailsViewState()

}