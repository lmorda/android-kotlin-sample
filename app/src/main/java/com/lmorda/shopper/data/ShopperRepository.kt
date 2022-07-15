package com.lmorda.shopper.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.lmorda.shopper.data.MockData.MOCK_STATUSES
import com.lmorda.shopper.data.models.FoodCategory
import com.lmorda.shopper.data.models.FoodItem
import com.lmorda.shopper.data.paging.PreviousItemsPagingSource
import com.lmorda.shopper.utils.EspressoIdlingResource
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ShopperRepository(private val apiService: CartApiService) {

    /**
     * Get the food items at a store using paging
     */
    suspend fun getStoreItems(category: FoodCategory): List<FoodItem> {
        return apiService.getStoreItems(category = category)
    }

    /**
     * Get the buy again food items at a store using paging
     */
    val previousItems = Pager(PagingConfig(pageSize = 10)) {
        PreviousItemsPagingSource(apiService)
    }.flow

    /**
     * Get the details for a food item
     */
    suspend fun getFoodDetails(foodItemId: Int?): FoodItem? {
        return apiService.getFoodItemDetails(foodItemId)
    }

    /**
     * Create a new order at a store (only one store right now!)
     */
    suspend fun createOrder() = apiService.createOrder()

    /**
     * Shopping cart information including cart items, number of items, and amount total
     */
    suspend fun getCartItems() = apiService.getCartItems()
    suspend fun getCartNum() = apiService.getCartItems().size
    suspend fun getOrderTotal() = apiService.getOrderTotal()

    /**
     * Update the cart by either adding or removing an item
     */
    fun updateCart(foodItem: FoodItem, isAdd: Boolean): Boolean {
        if (isAdd) apiService.addItemToCart(foodItem)
        else apiService.removeItemFromCart(foodItem)
        return true
    }

    /**
     * Order details, simulating the map updating, driver status, etc.
     */
    fun getOrderDetails(orderNum: Int) = apiService.getArrivalDetails(orderNum)

    /**
     * Poll the order status after the user has initiated checkout
     */
    private val POLLING_DELAY = 1000L
    val processOrder: Flow<String> =
        flow {
            EspressoIdlingResource.increment()
            while (true) {
                delay(POLLING_DELAY)
                val latestStatus = apiService.getCheckoutStatus()
                emit(latestStatus)
                if (latestStatus == MOCK_STATUSES.first()) EspressoIdlingResource.decrement()
                if (latestStatus == MOCK_STATUSES.last()) break
            }
        }

    /**
     * Previously purchased order list
     */
    suspend fun getOrders() = apiService.getOrders()
}