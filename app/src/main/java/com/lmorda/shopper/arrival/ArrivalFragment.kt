package com.lmorda.shopper.arrival

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.lmorda.shopper.R
import com.lmorda.shopper.databinding.FragmentArrivalBinding
import com.lmorda.shopper.utils.getViewModelFactory
import com.lmorda.shopper.utils.parseISO8601
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ArrivalFragment: Fragment() {

    private val viewModel by viewModels<ArrivalViewModel> { getViewModelFactory() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentArrivalBinding.inflate(inflater, container, false)

        lifecycleScope.launch {
            viewModel.orderDetails.collectLatest {
                binding.arrivalStatus.text = it?.status
                binding.arrivalTime.text = getString(R.string.arrival_times,
                        it?.arrivalFirst?.parseISO8601(),
                        it?.arrivalSecond?.parseISO8601())
                binding.statusDetails.text = it?.statusDetails
            }
        }

        binding.chatEditText.setOnEditorActionListener { view, actionId, _ ->
            return@setOnEditorActionListener when (actionId) {
                EditorInfo.IME_ACTION_SEND -> {
                    view.text = ""
                    showMessageSent()
                    true
                }
                else -> false
            }
        }

        binding.closeOrderBtn.setOnClickListener {
            findNavController().navigate(R.id.action_arrivalFragment_to_storeFragment)
        }

        return binding.root
    }

    private fun showMessageSent() {
        view?.let {
            val imm = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
            imm?.hideSoftInputFromWindow(it.windowToken, 0)
            Snackbar.make(it, "Message sent", Snackbar.LENGTH_SHORT).show()
        }
    }
}