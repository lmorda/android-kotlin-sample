package com.lmorda.shopper

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.lmorda.shopper.invite.InviteAFriend
import com.lmorda.shopper.utils.EspressoIdlingResource
import org.hamcrest.Matchers.allOf
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class StorePageTests {

    @Before
    fun registerIdlingResource() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.countingIdlingResource)
    }

    @After
    fun unregisterIdlingResource() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.countingIdlingResource)
    }

    @Test
    fun testStorePageLaunchesAfterSplash() {
        val activityScenario = ActivityScenario.launch(ShopperActivity::class.java)
        onView(withId(R.id.storeTitle)).check(matches(withText("Jons")))
        activityScenario.close()
    }

    @Test
    fun testPillLaunchesCart() {
        val activityScenario = ActivityScenario.launch(ShopperActivity::class.java)
        onView(withId(R.id.storeTitle)).check(matches(withText("Jons")))

        // No items in cart, does not go to cart
        onView(withId(R.id.cart_pill)).perform(click())
        onView(withId(R.id.storeTitle)).check(matches(withText("Jons")))

        // Add item to cart, then go to cart
        onView(allOf(withId(R.id.cbItem), hasSibling(withText("Wurth Ketchup"))))
            .perform(click())
        onView(withId(R.id.cart_pill)).perform(click())
        onView(withId(R.id.cartHeader)).check(matches(withText("Shopping Cart")))

        // Go back to store page
        Espresso.pressBack()
        onView(withId(R.id.storeTitle)).check(matches(withText("Jons")))

        // Clear cart for next test
        onView(allOf(withId(R.id.cbItem), hasSibling(withText("Wurth Ketchup"))))
            .perform(click())
        onView(withId(R.id.numItems)).check(matches(withText("0")))
        activityScenario.close()

    }

    @Test
    fun testCheckedItemsStateSaved() {
        val activityScenario = ActivityScenario.launch(ShopperActivity::class.java)
        onView(withId(R.id.storeTitle)).check(matches(withText("Jons")))

        // No items in cart, does not go to cart
        onView(withId(R.id.cart_pill)).perform(click())
        onView(withId(R.id.storeTitle)).check(matches(withText("Jons")))

        // Add item to cart, then go to cart
        onView(allOf(withId(R.id.cbItem), hasSibling(withText("Wurth Ketchup"))))
            .perform(click())
        onView(withId(R.id.cart_pill)).perform(click())
        onView(withId(R.id.cartHeader)).check(matches(withText("Shopping Cart")))

        // Go back to store page, verify item still in cart
        Espresso.pressBack()
        onView(withId(R.id.storeTitle)).check(matches(withText("Jons")))
        onView(allOf(withId(R.id.cbItem), hasSibling(withText("Wurth Ketchup"))))
            .check(matches(isChecked()))
        onView(withId(R.id.numItems)).check(matches(withText("1")))

        // Remove item from cart, verify number is correct
        onView(allOf(withId(R.id.cbItem), hasSibling(withText("Wurth Ketchup"))))
            .perform(click())
        onView(withId(R.id.numItems)).check(matches(withText("0")))
        activityScenario.close()

    }

    @get:Rule
    val composeTestRule = createComposeRule()
    @Test
    fun testCompletedOrder() {
        val activityScenario = ActivityScenario.launch(ShopperActivity::class.java)
        // Add item to cart, then go to cart
        onView(allOf(withId(R.id.cbItem), hasSibling(withText("Wurth Ketchup"))))
            .perform(click())
        onView(withId(R.id.numItems)).check(matches(withText("1")))
        onView(withId(R.id.cart_pill)).perform(click())
        // Click complete purchase
        onView(withId(R.id.btnPlaceOrder)).perform(click())
        // Verify confirming order has begun
        onView(withId(R.id.orderStatus)).check(matches(withText("Verifying payment card")))
        // Verify invite dialog is shown
        composeTestRule.onNodeWithText("Enjoy your order!").assertIsDisplayed()
        // Click invite close button
        composeTestRule.onNode(hasTestTag("CloseButton")).performClick()
        // Verify sent back to home page
        onView(withId(R.id.storeTitle)).check(matches(withText("Jons")))
        onView(withId(R.id.numItems)).check(matches(withText("0")))
        activityScenario.close()
    }
}