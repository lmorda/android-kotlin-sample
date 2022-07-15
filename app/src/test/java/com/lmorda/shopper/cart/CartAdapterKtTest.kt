package com.lmorda.shopper.cart

import com.lmorda.shopper.MAX_PRICE
import getPriceText
import org.junit.Assert.assertEquals
import org.junit.Test
import twoDecimalsFloor

class CartAdapterKtTest {
    @Test
    fun testGetPriceText() {
        var price = 10.00
        assertEquals(price.getPriceText(), "$10.00")
        price = 10.01
        assertEquals(price.getPriceText(), "$10.01")
        price = -1.00
        assertEquals(price.getPriceText(), "$0.00")
        price = 0.0
        assertEquals(price.getPriceText(), "$0.00")
        price = 99.9999
        assertEquals(price.getPriceText(), "$99.99")
        // Anything more than this must be a bug
        price = MAX_PRICE + 1
        assertEquals(price.getPriceText(), "$0.00")
    }

    @Test
    fun getTwoDecimals() {
        var number = 10.999
        assertEquals(number.twoDecimalsFloor(), "10.99")
        number = 10.9
        assertEquals(number.twoDecimalsFloor(), "10.90")
    }
}