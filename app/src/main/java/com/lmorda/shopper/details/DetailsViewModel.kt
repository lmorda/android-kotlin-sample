package com.lmorda.shopper.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lmorda.shopper.FOOD_ITEM_ID_ARG
import com.lmorda.shopper.data.ShopperRepository
import com.lmorda.shopper.data.models.FoodItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailsViewModel(
    private val shopperRepository: ShopperRepository,
    private val state: SavedStateHandle
) : ViewModel() {

    private val itemId: Int by lazy {
        state[FOOD_ITEM_ID_ARG] ?: -1
    }

    private val _detailsViewState = MutableStateFlow<DetailsViewState>(DetailsViewState.Loading)
    val detailsViewState: StateFlow<DetailsViewState> = _detailsViewState

    init {
        viewModelScope.launch {
            val details = shopperRepository.getFoodDetails(itemId)
            _detailsViewState.value = DetailsViewState.DetailsItem(foodItem = details)
        }
    }

}