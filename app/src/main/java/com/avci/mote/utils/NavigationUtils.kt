package com.avci.mote.utils

import android.os.Bundle
import android.view.MenuItem
import androidx.annotation.IdRes
import androidx.annotation.NonNull
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.navigation.fragment.FragmentNavigator
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.avci.mote.R

fun BottomNavigationView.setupWithNavController(
    navController: NavController,
    onMenuItemClicked: (item: MenuItem) -> Unit
) {
    setOnItemSelectedListener { item ->
        onMenuItemClicked(item)
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

fun NavController.navigateSafe(@NonNull directions: NavDirections, onError: (() -> Unit)? = null) {
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

fun NavController.navigateSafe(directions: NavDirections, extras: FragmentNavigator.Extras) {
    try {
        navigate(directions, extras)
    } catch (exception: IllegalArgumentException) {
        println(exception)
    }
}

fun <T> Fragment.setFragmentNavigationResult(key: String, value: T) {
    setFragmentResult(
        requestKey = key,
        result = bundleOf(key to value)
    )
}

fun <T> Fragment.useFragmentResultListenerValue(key: String, result: (T) -> Unit) {
    setFragmentResultListener(
        requestKey = key,
        listener = { requestKey, bundle ->
            try {
                val value = bundle.get(requestKey) as T
                result.invoke(value)
            } catch (classCastException: ClassCastException) {
                println(classCastException)
            }
        }
    )
}
