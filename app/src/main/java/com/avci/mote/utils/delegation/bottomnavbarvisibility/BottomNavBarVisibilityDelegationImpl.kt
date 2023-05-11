package com.avci.mote.utils.delegation.bottomnavbarvisibility

import com.avci.mote.modules.core.ui.BaseFragment
import com.avci.mote.utils.delegation.keyboardvisibility.KeyboardHandlerDelegation
import com.avci.mote.utils.delegation.keyboardvisibility.KeyboardHandlerDelegationImpl

class BottomNavBarVisibilityDelegationImpl : BottomNavBarVisibilityDelegation,
    KeyboardHandlerDelegation by KeyboardHandlerDelegationImpl() {

    private var baseFragment: BaseFragment? = null

    private val onKeyboardClosedListener = KeyboardHandlerDelegationImpl.OnKeyboardClosedListener {
        baseFragment?.handleBottomBarVisibility(true)
    }

    private val onKeyboardOpenedListener = KeyboardHandlerDelegationImpl.OnKeyboardOpenedListener {
        baseFragment?.handleBottomBarVisibility(false)
    }

    override fun registerBottomNavBarVisibilityDelegation(baseFragment: BaseFragment) {
        this.baseFragment = baseFragment
        registerKeyboardHandlerDelegation(
            baseFragment = baseFragment,
            onKeyboardClosedListener = onKeyboardClosedListener,
            onKeyboardOpenedListener = onKeyboardOpenedListener
        )
    }
}
