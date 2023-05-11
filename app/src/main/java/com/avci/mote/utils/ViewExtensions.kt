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

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun ImageView.setImageResAndVisibility(@DrawableRes imageResId: Int?) {
    if (imageResId != null && imageResId != -1) {
        setImageResource(imageResId)
    }
    isVisible = imageResId != null && imageResId != -1
}

fun TextView.setTextAndVisibility(text: String?) {
    setText(text)
    isVisible = text.isNullOrEmpty().not()
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

fun MaterialButton.setIconAndVisibility(@DrawableRes iconResId: Int?, @ColorRes iconColorResId: Int = -1) {
    if (iconResId != null && iconResId != -1) {
        setIconResource(iconResId)
    }
    isVisible = iconResId != null && iconResId != -1

    if (iconColorResId != -1) {
        setIconTintResource(iconColorResId)
    }
}

fun TabLayout.Tab?.changeTabTextAppearance(@StyleRes styleRes: Int) {
    if ((this?.view?.childCount ?: 0) >= 2 && this?.view?.get(1) is MaterialTextView) {
        val textView = view[1] as MaterialTextView
        textView.changeTextAppearance(styleRes)
    }
}

fun TextView.changeTextAppearance(@StyleRes styleRes: Int) {
    setTextAppearance(styleRes)
}

fun TextView.setClickActionAndVisibility(action: () -> Unit) {
    setOnClickListener { action.invoke() }
    show()
}

fun EditText.onAction(action: Int, runAction: () -> Unit) {
    this.setOnEditorActionListener { _, actionId, _ ->
        return@setOnEditorActionListener when (actionId) {
            action -> {
                runAction.invoke()
                true
            }
            else -> false
        }
    }
}
