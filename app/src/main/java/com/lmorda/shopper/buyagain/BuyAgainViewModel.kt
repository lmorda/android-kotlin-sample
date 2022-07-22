package com.lmorda.shopper.buyagain

import androidx.lifecycle.*
import com.lmorda.shopper.data.ShopperRepository
import com.lmorda.shopper.data.models.FoodItem
import kotlinx.coroutines.launch

class BuyAgainViewModel(private val shopperRepository: ShopperRepository) : ViewModel() {

    val buyAgainItems = shopperRepository.previousItems

    private val _cartUpdated = MutableLiveData<Boolean>()
    val cartUpdated: LiveData<Boolean> = _cartUpdated

    private val _cartNum = MutableLiveData<Int>()
    val cartNum: LiveData<Int> = _cartNum

    private val _orderCreated = MutableLiveData<Boolean>()
    val orderCreated: LiveData<Boolean> = _orderCreated

    fun getCartNum() = viewModelScope.launch {
        _cartNum.postValue(shopperRepository.getCartNum())
    }

    fun updateCart(foodItem: FoodItem, isAdd: Boolean) =
        viewModelScope.launch {
            _cartUpdated.postValue(shopperRepository.updateCart(foodItem, isAdd))
        }


    fun createOrder() =
        viewModelScope.launch {
            _orderCreated.postValue(shopperRepository.createOrder())
        }
}
