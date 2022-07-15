package com.lmorda.shopper.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.lmorda.shopper.data.CartApiService
import com.lmorda.shopper.data.models.FoodItem

class PreviousItemsPagingSource(
    private val apiService: CartApiService
) : PagingSource<Int, FoodItem>() {
    override suspend fun load(
        params: LoadParams<Int>
    ): LoadResult<Int, FoodItem> {
        try {
            val nextPageNumber = params.key ?: 1
            val response = apiService.getPreviousItems(nextPageNumber)
            return LoadResult.Page(
                data = response.items,
                prevKey = null,
                nextKey = response.nextPage
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, FoodItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}