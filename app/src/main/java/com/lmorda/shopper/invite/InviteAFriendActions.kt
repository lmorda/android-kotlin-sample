package com.lmorda.shopper.invite

import android.util.Log
import androidx.navigation.NavController
import com.lmorda.shopper.R

class InviteAFriendActions(override val navController: NavController?) : InviteFriendActions

interface InviteFriendActions {

    val navController: NavController?

    fun closeClickListener() = navController?.navigate(R.id.action_inviteFragment_to_arrivalFragment)
    fun addToCalendarClickListener() = Log.d("Shopper", "TODO: addToCalendarClickListener")
    fun shareThisOrderClickListener() = Log.d("Shopper", "TODO: shareThisOrderClickListener")
    fun learnMoreClickListener() = Log.d("Shopper", "TODO: learnMoreClickListener")
    fun messageClickListener() = Log.d("Shopper", "TODO: messageClickListener")
    fun copyLinkClickListener() = Log.d("Shopper", "TODO: copyLinkClickListener")
    fun shareLinkClickListener() = Log.d("Shopper", "TODO: shareLinkClickListener")
}
