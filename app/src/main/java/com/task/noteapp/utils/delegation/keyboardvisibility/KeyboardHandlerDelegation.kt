package com.task.noteapp.utils.delegation.keyboardvisibility

import com.task.noteapp.modules.core.ui.BaseFragment

interface KeyboardHandlerDelegation {
    val isKeyboardVisible: Boolean

    fun registerKeyboardHandlerDelegation(
        baseFragment: BaseFragment,
        onKeyboardClosedListener: KeyboardHandlerDelegationImpl.OnKeyboardClosedListener? = null,
        onKeyboardOpenedListener: KeyboardHandlerDelegationImpl.OnKeyboardOpenedListener? = null
    )
}
