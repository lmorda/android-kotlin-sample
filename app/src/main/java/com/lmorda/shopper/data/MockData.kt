package com.lmorda.shopper.data

import com.lmorda.shopper.ORDER_COMPLETE
import com.lmorda.shopper.R
import com.lmorda.shopper.data.models.*

object MockData {
    val MOCK_STATUSES = listOf(
        "Verifying payment card",
        "Processing order",
        ORDER_COMPLETE
    )

    val MOCK_CART = mutableListOf<FoodItem>()
    val MOCK_STORE_ITEMS =
        listOf(
            FoodItem(0, 5.49, "Jim's Ground Beef", R.drawable.cow, FoodCategory.RecommendedForYou),
            FoodItem(1, 3.99, "Wurth Ketchup", R.drawable.ketchup, FoodCategory.RecommendedForYou),
            FoodItem(2, 5.20, "King Hamburger Buns", R.drawable.buns, FoodCategory.RecommendedForYou),
            FoodItem(3, 4.20, "Organic Mozarella Cheese", R.drawable.cheese, FoodCategory.RecommendedForYou),
            FoodItem(4, 4.49, "Organic Valley Apples", R.drawable.apple, FoodCategory.MostPopular),
            FoodItem(5, 2.99, "Whole Wheat Bread", R.drawable.bread, FoodCategory.MostPopular),
            FoodItem(6, 2.50, "Elite Coffee Beans", R.drawable.coffee_beans, FoodCategory.MostPopular),
            FoodItem(7, 6.75, "Best Eggs", R.drawable.egg, FoodCategory.MostPopular),
            FoodItem(8, 5.49, "Organic Whole Milk", R.drawable.milk_bottle, FoodCategory.MostPopular),
            FoodItem(9, 7.99, "Bananas from Ecuador", R.drawable.banana, FoodCategory.MostPopular),
            FoodItem(10, 4.99, "Dark Chocolate", R.drawable.chocolate, FoodCategory.WhatsNew),
            FoodItem(11, 3.49, "Celery Bunch", R.drawable.celery, FoodCategory.WhatsNew),
            FoodItem(12, 6.00, "Broccoli Florets", R.drawable.broccoli, FoodCategory.WhatsNew),
            FoodItem(13, 6.00, "Lettuce Head", R.drawable.lettuce, FoodCategory.WhatsNew),
            FoodItem(14, 2.29, "Lemon Bag", R.drawable.lemon, FoodCategory.WhatsNew),
            FoodItem(15, 3.39, "Florida Oranges", R.drawable.orange, FoodCategory.WhatsNew),
            FoodItem(16, 2.00, "Cherry Tomatoes", R.drawable.tomato, FoodCategory.RecommendedForYou),
            FoodItem(
                17,
                5.00,
                "Whole Wheat Spaghetti",
                R.drawable.spaghetti,
                FoodCategory.RecommendedForYou
            ),
            FoodItem(18, 4.59, "Cookies", R.drawable.cookie, FoodCategory.RecommendedForYou),
            FoodItem(19, 3.00, "Chicken Drumsticks", R.drawable.chicken_leg, FoodCategory.RecommendedForYou),
            FoodItem(20, 3.00, "Irish Golden Butter", R.drawable.butter, FoodCategory.RecommendedForYou),
            FoodItem(21, 1.49, "Bluberry Muffins", R.drawable.muffin, FoodCategory.WhatsNew),
            FoodItem(22, 2.49, "Super Healthy Cereal", R.drawable.cereals, FoodCategory.WhatsNew),
            FoodItem(23, 2.99, "Protein Bar", R.drawable.bar, FoodCategory.WhatsNew),
            FoodItem(24, 4.00, "Hard Taco Shells", R.drawable.taco, FoodCategory.MostPopular),
            FoodItem(25, 4.50, "Strawberry Basket", R.drawable.strawberry, FoodCategory.MostPopular),
            FoodItem(26, 3.50, "Sugar Free Soda", R.drawable.soda, FoodCategory.MostPopular),
            FoodItem(
                27,
                5.20,
                "Crinkle Cut French Fries",
                R.drawable.french_fries,
                FoodCategory.MostPopular
            ),
            FoodItem(28, 6.00, "Cheesebuger", R.drawable.burger, FoodCategory.MostPopular),
            FoodItem(29, 7.30, "Dozen Donuts", R.drawable.donut, FoodCategory.MostPopular),
            FoodItem(30, 7.00, "Pepperoni Pizza", R.drawable.pizza, FoodCategory.MostPopular),
        )
    val MOCK_PREVIOUSLY_BOUGHT = listOf(
        StoreItems(items = MOCK_STORE_ITEMS.subList(0, 10), nextPage = 2),
        StoreItems(items = MOCK_STORE_ITEMS.subList(10, 20), nextPage = null)
    )

    var MOCK_ORDER_STATUS_STEP = 0
    const val MOCK_API_DELAY = 0L
    const val MOCK_ARRIVAL_UPDATE_DELAY = 10000L
    const val MOCK_ORDER_NUM = 0
    val MOCK_ARRIVALS = listOf(
        Arrival(
            orderNum = MOCK_ORDER_NUM,
            status = "Confirming your order",
            arrivalFirst = "2021-11-01T14:47:00Z",
            arrivalSecond = "2021-11-01T14:57:00Z",
            statusDetails = "We sent your order to Jons for final confirmation.",
            storeName = "Jons"
        ),
        Arrival(
            orderNum = MOCK_ORDER_NUM,
            status = "Order confirmed",
            arrivalFirst = "2021-11-01T14:49:00Z",
            arrivalSecond = "2021-11-01T14:54:00Z",
            statusDetails = "Order confirmed. Driver is waiting for your order.",
            storeName = "Jons"
        )
    )
    val MOCK_ORDERS = listOf(
        Order(
            id = 0,
            date = "2021-12-05T14:49:00Z",
            items = listOf(
                MOCK_STORE_ITEMS[0],
                MOCK_STORE_ITEMS[1],
                MOCK_STORE_ITEMS[2],
                MOCK_STORE_ITEMS[3],
                MOCK_STORE_ITEMS[4],
                MOCK_STORE_ITEMS[5],
                MOCK_STORE_ITEMS[12],
                MOCK_STORE_ITEMS[13],
                MOCK_STORE_ITEMS[14],
                MOCK_STORE_ITEMS[15]
            ),
            total = 123.39,
            storeName = "Jons"
        ),
        Order(
            id = 0,
            date = "2021-12-03T14:49:00Z",
            items = listOf(
                MOCK_STORE_ITEMS[10],
                MOCK_STORE_ITEMS[11],
                MOCK_STORE_ITEMS[12]
            ),
            total = 67.44,
            storeName = "Sprite Aid"
        ),
        Order(
            id = 0,
            date = "2021-12-01T14:49:00Z",
            items = listOf(
                MOCK_STORE_ITEMS[4],
                MOCK_STORE_ITEMS[3],
                MOCK_STORE_ITEMS[2],
                MOCK_STORE_ITEMS[8],
                MOCK_STORE_ITEMS[9],
                MOCK_STORE_ITEMS[11],
                MOCK_STORE_ITEMS[1],
                MOCK_STORE_ITEMS[2],
                MOCK_STORE_ITEMS[0],
                MOCK_STORE_ITEMS[5],
                MOCK_STORE_ITEMS[6],
                MOCK_STORE_ITEMS[7],
            ),
            total = 47.99,
            storeName = "Slimbo's"
        ),        Order(
            id = 0,
            date = "2021-11-19T14:49:00Z",
            items = listOf(
                MOCK_STORE_ITEMS[0],
                MOCK_STORE_ITEMS[1],
                MOCK_STORE_ITEMS[2],
                MOCK_STORE_ITEMS[3],
                MOCK_STORE_ITEMS[4],
                MOCK_STORE_ITEMS[5],
                MOCK_STORE_ITEMS[12],
                MOCK_STORE_ITEMS[13],
                MOCK_STORE_ITEMS[14],
                MOCK_STORE_ITEMS[15]
            ),
            total = 123.39,
            storeName = "Jons"
        ),
        Order(
            id = 0,
            date = "2021-11-13T14:49:00Z",
            items = listOf(
                MOCK_STORE_ITEMS[10],
                MOCK_STORE_ITEMS[11],
                MOCK_STORE_ITEMS[12]
            ),
            total = 67.44,
            storeName = "Sprite Aid"
        ),
        Order(
            id = 0,
            date = "2021-11-05T14:49:00Z",
            items = listOf(
                MOCK_STORE_ITEMS[4],
                MOCK_STORE_ITEMS[3],
                MOCK_STORE_ITEMS[2],
                MOCK_STORE_ITEMS[8],
                MOCK_STORE_ITEMS[9],
                MOCK_STORE_ITEMS[11],
                MOCK_STORE_ITEMS[1],
                MOCK_STORE_ITEMS[2],
                MOCK_STORE_ITEMS[0],
                MOCK_STORE_ITEMS[5],
                MOCK_STORE_ITEMS[6],
                MOCK_STORE_ITEMS[7],
            ),
            total = 47.99,
            storeName = "Slimbo's"
        ),
    )

}