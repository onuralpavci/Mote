package com.avci.mote.utils.delegation.keyboardvisibility

import com.avci.mote.modules.core.ui.BaseFragment

interface KeyboardHandlerDelegation {
    val isKeyboardVisible: Boolean

    fun registerKeyboardHandlerDelegation(
        baseFragment: BaseFragment,
        onKeyboardClosedListener: KeyboardHandlerDelegationImpl.OnKeyboardClosedListener? = null,
        onKeyboardOpenedListener: KeyboardHandlerDelegationImpl.OnKeyboardOpenedListener? = null
    )
}
