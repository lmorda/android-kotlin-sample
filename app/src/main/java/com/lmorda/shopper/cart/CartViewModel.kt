package com.lmorda.shopper.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.lmorda.shopper.data.ShopperRepository

class CartViewModel(private val shopperRepository: ShopperRepository) : ViewModel() {

    fun getCartItems() = liveData {
        emit(shopperRepository.getCartItems())
    }

    fun getOrderTotal() = liveData {
        emit(shopperRepository.getOrderTotal())
    }
}