package com.avci.mote.utils

import android.os.Bundle
import android.view.MenuItem
import androidx.annotation.IdRes
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import com.avci.mote.R
import com.google.android.material.bottomnavigation.BottomNavigationView

fun BottomNavigationView.setupWithNavController(
    navController: NavController
) {
    setOnItemSelectedListener { item ->
        return@setOnItemSelectedListener onNavDestinationChanged(item, navController)
    }
}

private fun onNavDestinationChanged(
    item: MenuItem,
    navController: NavController
): Boolean {
    val builder = NavOptions.Builder()
        .setPopUpTo(R.id.homeNavigation, false)
    navController.navigateSafe(item.itemId, null, builder.build())
    return true
}

fun NavController.navigateSafe(directions: NavDirections, onError: (() -> Unit)? = null) {
    try {
        navigate(directions)
    } catch (exception: IllegalArgumentException) {
        onError?.invoke()
        println(exception)
    }
}

fun NavController.navigateSafe(@IdRes resId: Int, args: Bundle?, navOptions: NavOptions) {
    try {
        navigate(resId, args, navOptions)
    } catch (exception: IllegalArgumentException) {
        println(exception)
    }
}
