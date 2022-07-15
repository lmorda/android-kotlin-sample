package com.lmorda.shopper.orders

import com.lmorda.shopper.data.models.OrderItems

sealed class OrderItemsViewState {

    object Loading : OrderItemsViewState()

    data class Error(val error: Throwable) : OrderItemsViewState()

    data class Items(val items: OrderItems) : OrderItemsViewState()

}