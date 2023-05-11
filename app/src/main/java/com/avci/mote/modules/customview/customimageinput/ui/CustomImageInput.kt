package com.avci.mote.modules.customview.customimageinput.ui

import android.content.Context
import android.graphics.drawable.Drawable
import android.net.Uri
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import com.avci.mote.R
import com.avci.mote.databinding.CustomImageInputBinding
import com.avci.mote.utils.hide
import com.avci.mote.utils.loadImageWithCachedFirst
import com.avci.mote.utils.show
import com.avci.mote.utils.viewbinding.viewBinding

class CustomImageInput @JvmOverloads constructor(
    context: Context,
    private val attrs: AttributeSet? = null
) : ConstraintLayout(context, attrs) {

    private val binding = viewBinding(CustomImageInputBinding::inflate)

    fun setOnEditButtonClickListener(callback: (() -> Unit)) {
        binding.editButton.setOnClickListener { callback.invoke() }
    }

    fun setOnCloseClickListener(callback: (() -> Unit)) {
        binding.deleteButton.setOnClickListener { callback.invoke() }
    }

    fun loadImage(uri: Uri, onLoadFailed: (() -> Unit)? = null) {
        binding.apply {
            context.loadImageWithCachedFirst(uri, uri, ::onResourceReady, ::onResourceReady, onLoadFailed)
        }
    }

    fun removeImage() {
        with(binding) {
            selectedImageView.setImageDrawable(null)
            selectedImageView.setBackgroundResource(R.drawable.bg_image_input_not_selected)
            placeholderView.show()
        }
    }

    private fun onResourceReady(drawable: Drawable) {
        with(binding) {
            selectedImageView.setImageDrawable(drawable)
            selectedImageView.background = null
            placeholderView.hide()
        }
    }
}
