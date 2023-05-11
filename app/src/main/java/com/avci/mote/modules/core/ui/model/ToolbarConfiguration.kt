package com.avci.mote.modules.core.ui.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class ToolbarConfiguration(
    @StringRes
    val titleStringResId: Int? = null,
    val onBackButtonClicked: (() -> Unit)? = null,
    @StringRes
    val endButtonStringResId: Int? = null,
    @DrawableRes
    val endButtonIconResId: Int? = null,
    val onEndButtonClicked: (() -> Unit)? = null,
    val onSearchBarTextChangeListener: ((text: String) -> Unit)? = null
)
