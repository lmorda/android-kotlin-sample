package com.lmorda.shopper.utils

import java.text.SimpleDateFormat
import java.util.*

fun String.parseISO8601(): String {
    val iso8601 = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
    val timestamp = SimpleDateFormat("MMMM d h:mmaa", Locale.getDefault())
    return try {
        iso8601.parse(this)?.let { timestamp.format(it) } ?: ""
    } catch (ex: Exception) {
        ""
    }
}