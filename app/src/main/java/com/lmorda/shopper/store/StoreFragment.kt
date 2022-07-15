package com.lmorda.shopper.store

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.BaseTransientBottomBar.LENGTH_SHORT
import com.google.android.material.snackbar.Snackbar
import com.lmorda.shopper.FOOD_ITEM_ID_ARG
import com.lmorda.shopper.R
import com.lmorda.shopper.data.models.FoodCategory
import com.lmorda.shopper.databinding.FragmentStoreBinding
import com.lmorda.shopper.utils.getViewModelFactory

class StoreFragment : Fragment() {

    private val viewModel by viewModels<StoreViewModel> { getViewModelFactory() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentStoreBinding.inflate(inflater, container, false)
        val view = binding.root
        var creatingOrder = false
        var numItems = 0

        fun getAdapter() = StoreItemAdapter(
            itemClickListener = {
                val bundle = bundleOf(FOOD_ITEM_ID_ARG to it)
                findNavController().navigate(R.id.action_storeFragment_to_detailsFragment, bundle)
            },
            checkListener = {
                viewModel.updateCart(it.first, it.second)
            })

        binding.recommendedForYou.adapter = getAdapter()
        binding.whatsNew.adapter = getAdapter()
        binding.mostPopular.adapter = getAdapter()

        viewModel.getStoreItems(FoodCategory.RecommendedForYou).observe(viewLifecycleOwner) {
            (binding.recommendedForYou.adapter as StoreItemAdapter).apply { submitList(it) }
        }

        viewModel.getStoreItems(FoodCategory.WhatsNew).observe(viewLifecycleOwner) {
            (binding.whatsNew.adapter as StoreItemAdapter).apply { submitList(it) }
        }

        viewModel.getStoreItems(FoodCategory.MostPopular).observe(viewLifecycleOwner) {
            (binding.mostPopular.adapter as StoreItemAdapter).apply { submitList(it) }
        }

        viewModel.cartNum.observe(viewLifecycleOwner) {
            numItems = it
            binding.numItems.text = it.toString()
        }

        viewModel.cartUpdated.observe(viewLifecycleOwner) {
            viewModel.getCartNum()
        }

        binding.cartPill.setOnClickListener {
            if (creatingOrder || numItems == 0) return@setOnClickListener
            creatingOrder = true
            viewModel.createOrder().observe(viewLifecycleOwner, Observer {
                if (it == true) {
                    creatingOrder = false
                    findNavController().navigate(R.id.action_storeFragment_to_cartFragment)
                }
                else {
                    creatingOrder = false
                    Snackbar.make(view, resources.getString(R.string.create_order_error), LENGTH_SHORT).show()
                }
            })
        }

        viewModel.getCartNum()

        return view
    }

}
