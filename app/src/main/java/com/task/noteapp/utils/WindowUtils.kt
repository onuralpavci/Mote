package com.task.noteapp.utils

import android.content.Context

fun Context.getScreenWidth(): Int {
    val displayMetrics = resources.displayMetrics
    return displayMetrics.widthPixels
}
