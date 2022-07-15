package com.lmorda.shopper.buyagain

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.BaseTransientBottomBar.LENGTH_SHORT
import com.google.android.material.snackbar.Snackbar
import com.lmorda.shopper.FOOD_ITEM_ID_ARG
import com.lmorda.shopper.R
import com.lmorda.shopper.databinding.FragmentBuyAgainBinding
import com.lmorda.shopper.utils.getViewModelFactory
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class BuyAgainFragment : Fragment() {

    private val viewModel by viewModels<BuyAgainViewModel> { getViewModelFactory() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentBuyAgainBinding.inflate(inflater, container, false)
        val view = binding.root
        var creatingOrder = false
        var numItems = 0

        val adapter = BuyAgainAdapter(
            itemClickListener = {
                val bundle = bundleOf(FOOD_ITEM_ID_ARG to it)
                findNavController().navigate(R.id.action_buyAgainFragment_to_detailsFragment, bundle)
            },
            checkListener = {
                viewModel.updateCart(it.first, it.second)
            })
        binding.itemsList.adapter = adapter

        lifecycleScope.launch {
            viewModel.buyAgainItems.collectLatest { pagingData ->
                adapter.submitData(pagingData)
            }
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
            viewModel.createOrder().observe(viewLifecycleOwner) {
                if (it == true) {
                    creatingOrder = false
                    findNavController().navigate(R.id.action_buyAgainFragment_to_cartFragment)
                } else {
                    creatingOrder = false
                    Snackbar.make(
                        view,
                        resources.getString(R.string.create_order_error),
                        LENGTH_SHORT
                    ).show()
                }
            }
        }
        viewModel.getCartNum()

        return view
    }

}
