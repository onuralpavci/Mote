package com.task.noteapp.modules.customview.customimageinput.ui

import android.content.Context
import android.graphics.drawable.Drawable
import android.net.Uri
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import com.task.noteapp.R
import com.task.noteapp.databinding.CustomImageInputBinding
import com.task.noteapp.utils.hide
import com.task.noteapp.utils.loadImageWithCachedFirst
import com.task.noteapp.utils.show
import com.task.noteapp.utils.viewbinding.viewBinding

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
