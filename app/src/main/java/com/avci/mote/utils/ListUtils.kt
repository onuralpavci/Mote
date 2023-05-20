package com.avci.mote.utils

fun <T> MutableList<T>.safeRemoveAt(index: Int): T? {
    return if (index in 0 until size) {
        removeAt(index)
    } else {
        null
    }
}
