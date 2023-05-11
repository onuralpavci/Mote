package com.task.noteapp.modules.customview.customscreenstate.ui.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class ScreenStateConfiguration(
    @StringRes
    val titleResId: Int,
    @DrawableRes
    val startImageResId: Int? = null,
    @StringRes
    val descriptionResId: Int? = null
)
