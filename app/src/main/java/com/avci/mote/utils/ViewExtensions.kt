package com.avci.mote.utils

import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.annotation.StyleRes
import androidx.core.view.get
import androidx.core.view.isVisible
import com.google.android.material.button.MaterialButton
import com.google.android.material.tabs.TabLayout
import com.google.android.material.textview.MaterialTextView

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.hide() {
    this.visibility = View.GONE
}

fun ImageView.setImageResAndVisibility(@DrawableRes imageResId: Int?) {
    if (imageResId != null && imageResId != -1) {
        setImageResource(imageResId)
    }
    isVisible = imageResId != null && imageResId != -1
}

fun TextView.setTextAndVisibility(@StringRes stringRes: Int?) {
    if (stringRes != null && stringRes != -1) {
        setText(stringRes)
    }
    isVisible = stringRes != null && stringRes != -1
}

fun MaterialButton.setTextAndVisibility(@StringRes stringRes: Int?) {
    if (stringRes != null && stringRes != -1) {
        setText(stringRes)
    }
    isVisible = stringRes != null && stringRes != -1
}

fun TextView.setClickActionAndVisibility(action: () -> Unit) {
    setOnClickListener { action.invoke() }
    show()
}
