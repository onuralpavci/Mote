package com.avci.mote.utils.delegation.bottomnavbarvisibility

import com.avci.mote.modules.core.ui.BaseFragment

interface BottomNavBarVisibilityDelegation {
    fun registerBottomNavBarVisibilityDelegation(baseFragment: BaseFragment)
}
