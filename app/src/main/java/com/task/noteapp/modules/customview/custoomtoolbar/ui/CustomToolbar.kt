package com.task.noteapp.modules.customview.custoomtoolbar.ui

import android.content.Context
import android.util.AttributeSet
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.task.noteapp.R
import com.task.noteapp.databinding.CustomToolbarBinding
import com.task.noteapp.modules.core.ui.model.ToolbarConfiguration
import com.task.noteapp.utils.hide
import com.task.noteapp.utils.setClickActionAndVisibility
import com.task.noteapp.utils.setTextAndVisibility
import com.task.noteapp.utils.show
import com.task.noteapp.utils.viewbinding.viewBinding

class CustomToolbar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : ConstraintLayout(context, attrs) {

    private val binding = viewBinding(CustomToolbarBinding::inflate)

    init {
        initRootView()
    }

    fun configure(toolbarConfiguration: ToolbarConfiguration?) {
        if (toolbarConfiguration == null) {
            hide()
            return
        }
        with(toolbarConfiguration) {
            initBackButton(onBackButtonClicked = onBackButtonClicked)
            initTitle(titleResId = titleStringResId)
            initEndButton(
                endButtonStringResId = endButtonStringResId,
                endButtonIconResId = endButtonIconResId,
                onEndButtonClicked = onEndButtonClicked
            )
            initSearchBar(onSearchBarTextChangeListener)
        }
        show()
    }

    fun setSearchText(text: String?) {
        binding.searchBarCustomLayout.apply {
            if (text == null || text.isBlank()) {
                clearInput()
            } else {
                this.text = text
            }
        }
    }

    private fun initBackButton(onBackButtonClicked: (() -> Unit)? = null) {
        binding.backButton.setClickActionAndVisibility { onBackButtonClicked?.invoke() }
        with(binding.backButton) {
            if (onBackButtonClicked == null) {
                hide()
                return
            }
            setOnClickListener {
                onBackButtonClicked.invoke()
            }
            show()
        }
    }

    private fun initTitle(titleResId: Int?) {
        binding.titleTextView.setTextAndVisibility(titleResId)
    }

    private fun initEndButton(
        @StringRes endButtonStringResId: Int?,
        @DrawableRes endButtonIconResId: Int?,
        onEndButtonClicked: (() -> Unit)?
    ) {
        binding.endButton.apply {
            setTextAndVisibility(endButtonStringResId)
            setOnClickListener { onEndButtonClicked?.invoke() }
            endButtonIconResId?.let { setIconResource(it) }
        }
    }

    private fun initSearchBar(onSearchBarTextChangeListener: ((text: String) -> Unit)?) {
        with(binding.searchBarCustomLayout) {
            if (onSearchBarTextChangeListener == null) {
                hide()
                return
            } else {
                setOnTextChangeListener { onSearchBarTextChangeListener.invoke(it) }
                show()
            }
        }
    }

    private fun initRootView() {
        setBackgroundColor(ContextCompat.getColor(context, R.color.primary_background))
    }
}
