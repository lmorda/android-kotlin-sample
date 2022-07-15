package com.lmorda.shopper.settings

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.lmorda.shopper.R

class SettingsFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences, rootKey)
    }
}