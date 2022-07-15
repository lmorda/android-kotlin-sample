package com.lmorda.shopper.data

import android.util.Log
import com.lmorda.shopper.data.MockData.MOCK_API_DELAY
import com.lmorda.shopper.data.MockData.MOCK_ARRIVALS
import com.lmorda.shopper.data.MockData.MOCK_ARRIVAL_UPDATE_DELAY
import com.lmorda.shopper.data.MockData.MOCK_CART
import com.lmorda.shopper.data.MockData.MOCK_ORDERS
import com.lmorda.shopper.data.MockData.MOCK_ORDER_NUM
import com.lmorda.shopper.data.MockData.MOCK_ORDER_STATUS_STEP
import com.lmorda.shopper.data.MockData.MOCK_PREVIOUSLY_BOUGHT
import com.lmorda.shopper.data.MockData.MOCK_STATUSES
import com.lmorda.shopper.data.MockData.MOCK_STORE_ITEMS
import com.lmorda.shopper.data.models.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow

class CartApiService {
    /**
     * Get a list of food items at a store (only one store right now!)
     */
    suspend fun getStoreItems(category: FoodCategory): List<FoodItem> {
        Log.d("shopper", "GET /v1/store/items?category=$category")
        delay(MOCK_API_DELAY)
        Log.d("shopper", "SUCCESS /v1/store/items?category=$category\n" +
                MOCK_STORE_ITEMS.filter { it.category == category })
        return MOCK_STORE_ITEMS.filter { it.category == category }
    }

    /**
     * Get a list of food items a user can buy again
     */
    suspend fun getPreviousItems(pageNum: Int): StoreItems {
        Log.d("shopper", "GET /v1/store/items/previous")
        delay(MOCK_API_DELAY)
        Log.d("shopper", "SUCCESS /v1/store/previous\n" + MOCK_PREVIOUSLY_BOUGHT[pageNum - 1])
        return MOCK_PREVIOUSLY_BOUGHT[pageNum - 1]
    }

    /**
     * Get the details about a food item
     */
    suspend fun getFoodItemDetails(foodItemId: Int?): FoodItem? {
        Log.d("shopper", "GET /v1/store/items/$foodItemId")
        delay(MOCK_API_DELAY)
        Log.d(
            "shopper",
            "SUCCESS /v1/store/items/\n" + foodItemId + " " +
                    MOCK_STORE_ITEMS.find { it.id == foodItemId })
        return MOCK_STORE_ITEMS.find { it.id == foodItemId }
    }

    /**
     * Get summary of all orders
     */
    suspend fun getOrders(): OrderItems {
        Log.d("shopper", "GET /v1/store/orders")
        Log.d("shopper", MOCK_ORDERS.toString())
        Log.d("shopper", "SUCCESS /v1/order")
        delay(MOCK_API_DELAY)
        return OrderItems(MOCK_ORDERS)
    }

    /**
     * Create a new order
     */
    suspend fun createOrder(): Boolean {
        Log.d("shopper", "POST /v1/order")
        delay(MOCK_API_DELAY)
        Log.d("shopper", "SUCCESS /v1/order")
        return true
    }

    /**
     * Add ad item to the cart
     */
    fun addItemToCart(foodItem: FoodItem): Boolean {
        foodItem.inCart = true
        MOCK_CART.add(foodItem)
        MOCK_STORE_ITEMS.find { it.id == foodItem.id }?.inCart = true
        return true
    }

    /**
     * Remove an item that was added to the cart
     */
    fun removeItemFromCart(foodItem: FoodItem): Boolean {
        MOCK_CART.remove(foodItem)
        MOCK_STORE_ITEMS.find { it.id == foodItem.id }?.inCart = false
        return true
    }

    /**
     * Get the list of items currently in the cart
     */
    suspend fun getCartItems(): List<FoodItem> {
        Log.d("shopper", "GET /v1/cart/items")
        delay(MOCK_API_DELAY)
        Log.d("shopper", "SUCCESS /v1/cart/items\n" + MOCK_CART)
        return MOCK_CART
    }

    /**
     * Get the order total by summing all of the items in the cart
     */
    suspend fun getOrderTotal(): Double {
        Log.d("shopper", "GET /v1/order/total")
        delay(MOCK_API_DELAY)
        Log.d("shopper", "SUCCESS /v1/order/total")
        return MOCK_CART.sumOf { it.price }
    }

    /**
     * Get the status of the checkout process, includes verifying payment method,
     * processing order, and checkout complete statuses
     */
    suspend fun getCheckoutStatus(): String {
        Log.d("shopper", "GET /v1/order/status")
        delay(MOCK_API_DELAY)
        val status = MOCK_STATUSES[MOCK_ORDER_STATUS_STEP]
        MOCK_ORDER_STATUS_STEP++
        if (MOCK_ORDER_STATUS_STEP == MOCK_STATUSES.size) {
            MOCK_CART.clear()
            MOCK_STORE_ITEMS.forEach { it.inCart = false }
            MOCK_ORDER_STATUS_STEP = 0
        }
        Log.d("shopper", "SUCCESS /v1/order/status $status")
        return status
    }

    /**
     * Get arrival details for the order including driver status and arrival time.  Simulates
     * some type of real-time server connection using a flow
     */
    fun getArrivalDetails(orderNum: Int) = flow {
        if (orderNum != MOCK_ORDER_NUM) emit(null)
        var updateNum = 0
        while (updateNum < MOCK_ARRIVALS.size) {
            emit(MOCK_ARRIVALS[updateNum])
            updateNum++
            delay(MOCK_ARRIVAL_UPDATE_DELAY)
        }
    }

}