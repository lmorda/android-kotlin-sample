package com.lmorda.shopper

import com.lmorda.shopper.data.ShopperRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkObject
import org.junit.Assert.*
import org.junit.Test

class ServiceLocatorTest {

    @Test
    fun testProvideShopperRepository() {
        mockkObject(ServiceLocator)
        val repository = mockk<ShopperRepository>()
        every { ServiceLocator.provideShopperRepository() } returns repository
        assertEquals(ServiceLocator.provideShopperRepository(), repository)
    }


}