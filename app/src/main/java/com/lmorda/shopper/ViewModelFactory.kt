package com.lmorda.shopper

import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import com.lmorda.shopper.buyagain.BuyAgainViewModel
import com.lmorda.shopper.cart.CartViewModel
import com.lmorda.shopper.data.ShopperRepository
import com.lmorda.shopper.details.DetailsViewModel
import com.lmorda.shopper.arrival.ArrivalViewModel
import com.lmorda.shopper.orders.OrdersViewModel
import com.lmorda.shopper.status.StatusViewModel
import com.lmorda.shopper.store.StoreViewModel

@Suppress("UNCHECKED_CAST")
class ViewModelFactory constructor(
    private val shopperRepository: ShopperRepository,
    owner: SavedStateRegistryOwner,
    defaultArgs: Bundle? = null
) : AbstractSavedStateViewModelFactory(owner, defaultArgs) {

    override fun <T : ViewModel> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ) = with(modelClass) {
        when {
            isAssignableFrom(CartViewModel::class.java) -> CartViewModel(shopperRepository)
            isAssignableFrom(DetailsViewModel::class.java) -> DetailsViewModel(shopperRepository, handle)
            isAssignableFrom(StatusViewModel::class.java) -> StatusViewModel(shopperRepository)
            isAssignableFrom(StoreViewModel::class.java) -> StoreViewModel(shopperRepository)
            isAssignableFrom(BuyAgainViewModel::class.java) -> BuyAgainViewModel(shopperRepository)
            isAssignableFrom(ArrivalViewModel::class.java) -> ArrivalViewModel(shopperRepository, handle)
            isAssignableFrom(OrdersViewModel::class.java) -> OrdersViewModel(shopperRepository)
            else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }
    } as T
}