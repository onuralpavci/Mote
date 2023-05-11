package com.task.noteapp.modules.customview.customscreenstate.ui

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import com.task.noteapp.databinding.CustomScreenStateBinding
import com.task.noteapp.modules.customview.customscreenstate.ui.model.ScreenStateConfiguration
import com.task.noteapp.utils.hide
import com.task.noteapp.utils.setImageResAndVisibility
import com.task.noteapp.utils.setTextAndVisibility
import com.task.noteapp.utils.show
import com.task.noteapp.utils.viewbinding.viewBinding

class CustomScreenState @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : ConstraintLayout(context, attrs) {

    private val binding = viewBinding(CustomScreenStateBinding::inflate)

    fun configure(screenStateConfiguration: ScreenStateConfiguration?) {
        with(binding) {
            if (screenStateConfiguration == null) {
                root.hide()
                return
            }
            startImageView.setImageResAndVisibility(screenStateConfiguration.startImageResId)
            titleTextView.setText(screenStateConfiguration.titleResId)
            descriptionTextView.setTextAndVisibility(screenStateConfiguration.descriptionResId)
            root.show()
        }
    }
}
