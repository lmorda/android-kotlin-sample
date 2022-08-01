package com.lmorda.shopper.status

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.lmorda.shopper.ORDER_COMPLETE
import com.lmorda.shopper.R
import com.lmorda.shopper.STATUS_FRAGMENT
import com.lmorda.shopper.ShopperActivity
import com.lmorda.shopper.ShopperActivity.Companion.FRAGMENT_TAG
import com.lmorda.shopper.databinding.FragmentStatusBinding
import com.lmorda.shopper.utils.getViewModelFactory
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import nl.dionsegijn.konfetti.KonfettiView
import nl.dionsegijn.konfetti.models.Shape
import nl.dionsegijn.konfetti.models.Size

class StatusFragment : Fragment() {

    private val viewModel by viewModels<StatusViewModel> { getViewModelFactory() }

    private val CONFETTI_TIME = 3000L

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding = FragmentStatusBinding.inflate(inflater, container, false)
        val view = binding.root

        FRAGMENT_TAG = STATUS_FRAGMENT
        viewModel.getOrderStatus().observe(viewLifecycleOwner) {
            binding.orderStatus.text = it
            if (it == ORDER_COMPLETE) {
                showConfetti(binding.viewKonfetti)
                lifecycleScope.launch {
                    delay(CONFETTI_TIME)
                    findNavController().navigate(R.id.action_statusFragment_to_inviteFriendFragment)
                }
            }
        }

        viewModel.loading.observe(viewLifecycleOwner) {
            binding.pbLoading.isVisible = it
        }

        return view
    }

    private fun showConfetti(viewKonfetti: KonfettiView) {
        viewKonfetti.build()
            .addColors(Color.YELLOW, Color.GREEN, Color.MAGENTA)
            .setDirection(0.0, 359.0)
            .setSpeed(1f, 5f)
            .setFadeOutEnabled(true)
            .setTimeToLive(2000L)
            .addShapes(Shape.Square, Shape.Circle)
            .addSizes(Size(12))
            .setPosition(-50f, viewKonfetti.width + 50f, -50f, -50f)
            .streamFor(300, CONFETTI_TIME)
    }
}