package com.task.noteapp.utils

import android.content.Context
import android.graphics.drawable.Drawable
import android.net.Uri
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target

const val DEFAULT_IMAGE_CORNER_RADIUS = 12

fun ImageView.loadImage(uri: Uri) {
    Glide.with(this)
        .load(uri)
        .transform(RoundedCorners(DEFAULT_IMAGE_CORNER_RADIUS))
        .into(this)
}

fun ImageView.loadCircularImage(uri: Uri) {
    Glide.with(this)
        .load(uri)
        .circleCrop()
        .into(this)
}

fun Context.loadImage(uri: Uri, onResourceReady: (Drawable) -> Unit, onLoadFailed: (() -> Unit)? = null) {
    Glide.with(this)
        .load(uri)
        .transform(RoundedCorners(DEFAULT_IMAGE_CORNER_RADIUS))
        .listener(object : RequestListener<Drawable> {
            override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: Target<Drawable>?,
                isFirstResource: Boolean
            ): Boolean {
                onLoadFailed?.invoke()
                return true
            }

            override fun onResourceReady(
                resource: Drawable?,
                model: Any?,
                target: Target<Drawable>?,
                dataSource: DataSource?,
                isFirstResource: Boolean
            ): Boolean {
                if (resource != null) {
                    onResourceReady(resource)
                } else {
                    onLoadFailed?.invoke()
                }
                return true
            }
        })
        .preload()
}

fun Context.loadImageWithCachedFirst(
    uri: Uri,
    cachedUri: Uri,
    onCachedResourceReady: (Drawable) -> Unit,
    onResourceReady: (Drawable) -> Unit,
    onCachedLoadFailed: (() -> Unit)? = null,
    onLoadFailed: (() -> Unit)? = null
) {
    Glide.with(this)
        .load(cachedUri)
        .listener(object : RequestListener<Drawable> {
            override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: Target<Drawable>?,
                isFirstResource: Boolean
            ): Boolean {
                onLoadFailed?.invoke()
                return true
            }

            override fun onResourceReady(
                resource: Drawable?,
                model: Any?,
                target: Target<Drawable>?,
                dataSource: DataSource?,
                isFirstResource: Boolean
            ): Boolean {
                if (resource != null) {
                    onCachedResourceReady(resource)
                    this@loadImageWithCachedFirst.loadImage(uri, onResourceReady, onLoadFailed)
                } else {
                    onCachedLoadFailed?.invoke()
                }
                return true
            }
        })
        .preload()
}
