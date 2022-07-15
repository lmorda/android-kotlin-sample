package com.lmorda.shopper.buyagain

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.lmorda.shopper.R
import com.lmorda.shopper.data.models.FoodItem
import com.lmorda.shopper.databinding.FoodItemBinding

class BuyAgainAdapter(
    val itemClickListener: (Int) -> Unit,
    val checkListener: (Pair<FoodItem, Boolean>) -> Unit
) : PagingDataAdapter<FoodItem, BuyAgainAdapter.FoodItemViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<FoodItem>() {
            override fun areItemsTheSame(oldItem: FoodItem, newItem: FoodItem): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: FoodItem, newItem: FoodItem): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodItemViewHolder {
        return FoodItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.food_item,
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: FoodItemViewHolder, position: Int) {
        val cartItem = getItem(position)
        holder.bind(cartItem)
    }

    inner class FoodItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(foodItem: FoodItem?) {
            if (foodItem == null) return
            val binding = FoodItemBinding.bind(itemView)
            itemView.setOnClickListener { itemClickListener.invoke(foodItem.id) }
            with(binding) {
                itemName.text = foodItem.name
                itemImage.setImageDrawable(
                    ResourcesCompat.getDrawable(itemView.resources, foodItem.imageRes, null))
                cbItem.isChecked = foodItem.inCart
                cbItem.setOnCheckedChangeListener { _, isChecked ->
                    checkListener.invoke(Pair(foodItem, isChecked))
                }
            }
        }
    }
}