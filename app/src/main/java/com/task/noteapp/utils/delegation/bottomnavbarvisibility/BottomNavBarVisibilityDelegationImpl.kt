package com.task.noteapp.utils.delegation.bottomnavbarvisibility

import com.task.noteapp.modules.core.ui.BaseFragment
import com.task.noteapp.utils.delegation.keyboardvisibility.KeyboardHandlerDelegation
import com.task.noteapp.utils.delegation.keyboardvisibility.KeyboardHandlerDelegationImpl

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
