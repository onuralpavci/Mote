package com.task.noteapp.utils.delegation.bottomnavbarvisibility

import com.task.noteapp.modules.core.ui.BaseFragment

interface BottomNavBarVisibilityDelegation {
    fun registerBottomNavBarVisibilityDelegation(baseFragment: BaseFragment)
}
