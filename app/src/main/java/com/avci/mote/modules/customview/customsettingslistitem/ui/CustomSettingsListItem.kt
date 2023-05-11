package com.avci.mote.modules.customview.customsettingslistitem.ui

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import androidx.core.content.res.use
import com.avci.mote.R
import com.avci.mote.databinding.CustomSettingsListItemBinding
import com.avci.mote.utils.viewbinding.viewBinding

class CustomSettingsListItem @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : LinearLayout(context, attrs) {

    private val binding = viewBinding(CustomSettingsListItemBinding::inflate)

    init {
        initAttributes(attrs)
    }

    private fun initAttributes(attrs: AttributeSet?) {
        context.obtainStyledAttributes(attrs, R.styleable.CustomSettingsListItem).use {
            with(binding) {
                it.getText(R.styleable.CustomSettingsListItem_settingTitle)?.let { safeTitle ->
                    settingsTitleTextView.text = safeTitle
                }
                it.getText(R.styleable.CustomSettingsListItem_settingEndText)?.let { safeSubTitle ->
                    settingEndTextView.text = safeSubTitle
                }
                it.getDrawable(R.styleable.CustomSettingsListItem_settingIcon)?.let { safeIcon ->
                    settingIconImageView.setImageDrawable(safeIcon)
                }
            }
        }
    }
}
