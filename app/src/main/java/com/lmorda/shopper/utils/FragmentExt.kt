package com.lmorda.shopper.utils

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import com.lmorda.shopper.ServiceLocator
import com.lmorda.shopper.ViewModelFactory

fun Fragment.getViewModelFactory(arguments: Bundle? = null): AbstractSavedStateViewModelFactory {
    val shopperRepository = ServiceLocator.provideShopperRepository()
    return ViewModelFactory(shopperRepository, this, arguments)
}