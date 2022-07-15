package com.lmorda.shopper.orders

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lmorda.shopper.data.ShopperRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class OrdersViewModel(
    private val shopperRepository: ShopperRepository
) : ViewModel() {

    private val _orderItemsViewState = MutableStateFlow<OrderItemsViewState>(OrderItemsViewState.Loading)
    val orderItemsViewState: StateFlow<OrderItemsViewState> = _orderItemsViewState

    init {
        viewModelScope.launch {
            val orderItems = shopperRepository.getOrders()
            _orderItemsViewState.value = OrderItemsViewState.Items(items = orderItems)
        }
    }
}