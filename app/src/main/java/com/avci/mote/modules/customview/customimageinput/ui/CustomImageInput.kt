package com.avci.mote.modules.customview.customimageinput.ui

import android.content.Context
import android.graphics.drawable.Drawable
import android.net.Uri
import android.util.AttributeSet
import androidx.constraintlayout.motion.widget.MotionLayout
import com.avci.mote.R
import com.avci.mote.databinding.CustomImageInputBinding
import com.avci.mote.modules.customview.delegation.draggableitemviewdelegation.DraggableItemViewComponent
import com.avci.mote.modules.customview.delegation.draggableitemviewdelegation.DraggableItemViewDelegate
import com.avci.mote.utils.hide
import com.avci.mote.utils.loadImageWithCachedFirst
import com.avci.mote.utils.setImageResAndVisibility
import com.avci.mote.utils.show
import com.avci.mote.utils.viewbinding.viewBinding
import kotlin.properties.Delegates

class CustomImageInput @JvmOverloads constructor(
    context: Context,
    private val attrs: AttributeSet? = null
) : MotionLayout(context, attrs),
    DraggableItemViewComponent by DraggableItemViewDelegate() {

    private var isEditOptionsOpen by Delegates.observable(false) { _, _, newValue ->
        if (newValue) {
            binding.editButton.setImageResAndVisibility(R.drawable.ic_close)
            transitionToEnd()
        } else {
            binding.editButton.setImageResAndVisibility(R.drawable.ic_pencil)
            transitionToStart()
        }
    }

    private val binding = viewBinding(CustomImageInputBinding::inflate)

    override fun onFinishInflate() {
        super.onFinishInflate()
        initUi()
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        loadLayoutDescription(R.xml.custom_image_input_scene)
        isEditOptionsOpen = false
    }

    private fun initUi() {
        binding.editButton.setOnClickListener {
            isEditOptionsOpen = isEditOptionsOpen.not()
        }
        initDraggableItemViewComponent(binding.dragButton)
    }

    fun setOnSelectImageButtonClickListener(callback: (() -> Unit)) {
        binding.selectImageButton.setOnClickListener {
            callback.invoke()
        }
    }

    fun setOnDownloadImageButtonClickListener(callback: (() -> Unit)) {
        binding.downloadImageButton.setOnClickListener {
            callback.invoke()
        }
    }

    fun setOnDalleClickListener(callback: (() -> Unit)) {
        binding.dalleButton.setOnClickListener {
            callback.invoke()
        }
    }

    fun setOnCloseClickListener(callback: (() -> Unit)) {
        binding.deleteButton.setOnClickListener {
            callback.invoke()
        }
    }

    fun loadImage(uri: Uri, onLoadFailed: (() -> Unit)? = null) {
        binding.apply {
            context.loadImageWithCachedFirst(
                uri,
                uri,
                ::onResourceReady,
                ::onResourceReady,
                onLoadFailed
            )
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
