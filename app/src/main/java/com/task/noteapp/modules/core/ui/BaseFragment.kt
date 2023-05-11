package com.task.noteapp.modules.core.ui

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import com.task.noteapp.MainActivity
import com.task.noteapp.modules.core.ui.model.FragmentConfiguration
import com.task.noteapp.modules.core.ui.model.ToolbarConfiguration
import com.task.noteapp.modules.customview.custoomtoolbar.ui.CustomToolbar

abstract class BaseFragment(
    @LayoutRes private val layoutResId: Int,
) : Fragment(layoutResId) {

    abstract val fragmentConfiguration: FragmentConfiguration

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        customizeFragment()
    }

    fun navBack() {
        (activity as? MainActivity)?.navBack()
    }

    fun nav(directions: NavDirections) {
        (activity as? MainActivity)?.nav(directions)
    }

    private fun getAppToolbar(): CustomToolbar? {
        return (activity as? MainActivity)?.getToolbar()
    }

    private fun customizeFragment() {
        with(fragmentConfiguration) {
            setupToolbar(toolbarConfiguration)
            handleBottomBarVisibility(isBottomNavigationViewVisible)
        }
    }

    fun handleBottomBarVisibility(isBottomBarVisible: Boolean?) {
        if (isBottomBarVisible != null) {
            (activity as? MainActivity)?.isBottomBarNavigationVisible = isBottomBarVisible
        }
    }

    private fun setupToolbar(toolbarConfiguration: ToolbarConfiguration?) {
        getAppToolbar()?.configure(toolbarConfiguration)
    }
}
