package com.avci.mote.modules.customview.customactiondialog.ui.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class ActionDialogParams(
    @DrawableRes var startImageRes: Int? = null,
    @StringRes var titleRes: Int? = null,
    @StringRes var descriptionRes: Int? = null,
    @StringRes var primaryButtonTextRes: Int? = null,
    @StringRes var secondaryButtonTextRes: Int? = null,
    @StringRes var textInputLayoutHintTextRes: Int? = null,
    var primaryButtonClickListener: ((data: String?) -> Unit)? = null,
    var secondaryButtonClickListener: (() -> Unit)? = null
)
