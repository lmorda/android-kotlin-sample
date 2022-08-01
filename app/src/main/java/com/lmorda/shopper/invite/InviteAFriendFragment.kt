package com.lmorda.shopper.invite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.lmorda.shopper.INVITE_FRAGMENT
import com.lmorda.shopper.STATUS_FRAGMENT
import com.lmorda.shopper.ShopperActivity
import com.lmorda.shopper.ShopperActivity.Companion.FRAGMENT_TAG

class InviteAFriendFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            FRAGMENT_TAG = INVITE_FRAGMENT
            setContent {
                InviteAFriend(InviteAFriendActions(findNavController()))
            }
        }
    }

}