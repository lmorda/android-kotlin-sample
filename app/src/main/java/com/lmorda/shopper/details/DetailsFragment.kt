package com.lmorda.shopper.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.lmorda.shopper.utils.getViewModelFactory

class DetailsFragment : Fragment() {

    private val viewModel by viewModels<DetailsViewModel> { getViewModelFactory(arguments) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return ComposeView(requireContext()).apply {
            setContent {
                FoodItemDetails(viewModel) {
                    findNavController().popBackStack()
                }
            }
        }
    }

}