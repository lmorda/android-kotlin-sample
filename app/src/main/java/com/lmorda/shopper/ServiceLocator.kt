package com.lmorda.shopper

import com.lmorda.shopper.data.CartApiService
import com.lmorda.shopper.data.ShopperRepository

object ServiceLocator {

    @Volatile
    var shopperRepository: ShopperRepository? = null

    @Volatile
    var cartApiService: CartApiService? = null

    fun provideShopperRepository(): ShopperRepository {
        synchronized(this) {
            return shopperRepository ?: createShopperRepository()
        }
    }

    private fun provideCartApiService(): CartApiService {
        synchronized(this) {
            return cartApiService ?: createMockApiService()
        }
    }

    private fun createShopperRepository(): ShopperRepository {
        val shopperRepository = ShopperRepository(provideCartApiService())
        this.shopperRepository = shopperRepository
        return shopperRepository
    }

    private fun createMockApiService(): CartApiService {
        val cartService = CartApiService()
        this.cartApiService = cartService
        return cartService
    }

}