package com.lmorda.shopper.cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.lmorda.shopper.R
import com.lmorda.shopper.databinding.FragmentCartBinding
import com.lmorda.shopper.utils.getViewModelFactory
import getPriceText

class CartFragment : Fragment() {

    private val viewModel by viewModels<CartViewModel> { getViewModelFactory() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding = FragmentCartBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.cartItems.adapter = CartAdapter()

        viewModel.getCartItems().observe(viewLifecycleOwner) {
            (binding.cartItems.adapter as CartAdapter).apply {
                submitList(it)
            }
        }

        viewModel.getOrderTotal().observe(viewLifecycleOwner) {
            binding.orderTotal.text = getString(R.string.order_total, it.getPriceText())
        }

        binding.btnPlaceOrder.setOnClickListener {
            findNavController().navigate(R.id.action_cartFragment_to_statusFragment)
        }
        return view
    }

}