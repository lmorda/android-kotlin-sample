package com.lmorda.shopper.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lmorda.shopper.R
import com.lmorda.shopper.data.models.FoodCategory
import com.lmorda.shopper.data.models.FoodItem
import getPriceText

@Composable
fun FoodItemDetails(
    detailsViewModel: DetailsViewModel,
    backBtnListener: () -> Unit = {}
) {
    val detailsState by detailsViewModel.detailsViewState.collectAsState()
    when (detailsState) {
        is DetailsViewState.DetailsItem ->
            FoodItem((detailsState as DetailsViewState.DetailsItem).foodItem, backBtnListener)
        else -> {}
    }
}

@Composable
private fun FoodItem(
    foodItem: FoodItem? = null,
    backBtnListener: () -> Unit
) {
    if (foodItem == null) return
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Spacer(Modifier.height(16.dp))
        BackButton(backBtnListener = backBtnListener)
        FoodImage(foodItem, Modifier.align(Alignment.CenterHorizontally))
        FoodName(foodItem)
        FoodPrice(foodItem)
    }
}

@Composable
private fun FoodImage(foodItem: FoodItem, modifier: Modifier) {
    Image(
        painter = painterResource(id = foodItem.imageRes),
        contentDescription = stringResource(id = R.string.food_description),
        modifier = modifier.then(
            Modifier
                .height(200.dp)
                .width(200.dp)
                .padding(top = 32.dp)
        )
    )
}

@Composable
private fun BackButton(backBtnListener: () -> Unit) {
    Image(
        painter = painterResource(id = R.drawable.ic_baseline_arrow_back_24),
        contentDescription = stringResource(id = R.string.back_button_description),
        modifier = Modifier
            .padding(start = 16.dp)
            .clickable(
                enabled = true,
                onClick = {
                    backBtnListener()
                }
            )
    )
}

@Composable
private fun FoodName(foodItem: FoodItem) {
    Text(
        text = foodItem.name,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,
        color = Color.Black,
        modifier = Modifier.padding(start = 16.dp, top = 16.dp)
    )
}

@Composable
private fun FoodPrice(foodItem: FoodItem) {
    Text(
        text = foodItem.price.getPriceText(),
        fontSize = 22.sp,
        color = Color.Black,
        modifier = Modifier.padding(start = 16.dp, top = 16.dp)
    )
}

@Preview
@Composable
fun FoodItemDetailsPreview() {
    FoodItem(
        foodItem = FoodItem(
            id = 0,
            category = FoodCategory.MostPopular,
            name = "Hamburger",
            price = 5.99,
            imageRes = R.drawable.ic_baseline_fastfood_24
        ),
        backBtnListener = {

        }
    )
}
