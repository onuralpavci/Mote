package com.avci.mote.modules.customview.customscreenstate.ui

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import com.avci.mote.databinding.CustomScreenStateBinding
import com.avci.mote.modules.customview.customscreenstate.ui.model.ScreenStateConfiguration
import com.avci.mote.utils.hide
import com.avci.mote.utils.setImageResAndVisibility
import com.avci.mote.utils.setTextAndVisibility
import com.avci.mote.utils.show
import com.avci.mote.utils.viewbinding.viewBinding

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
