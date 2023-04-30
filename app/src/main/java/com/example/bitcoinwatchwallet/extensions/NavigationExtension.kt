package com.example.bitcoinwatchwallet.extensions

import androidx.navigation.NavController
import androidx.navigation.NavDirections

fun NavController.safeNavigate(directions: NavDirections) {
    try {
        navigate(directions)
    } catch (e: Exception) {
        // do nothing
    }
}