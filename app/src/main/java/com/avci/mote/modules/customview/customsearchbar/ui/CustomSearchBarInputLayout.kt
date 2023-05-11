package com.avci.mote.modules.customview.customsearchbar.ui

import android.content.Context
import android.text.InputFilter
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.res.use
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import com.avci.mote.R
import com.avci.mote.databinding.CustomSearchBarInputLayoutBinding
import com.avci.mote.utils.hide
import com.avci.mote.utils.viewbinding.viewBinding
import kotlin.properties.Delegates

// TODO: Create a base class for CustomInputLayouts
class CustomSearchBarInputLayout @JvmOverloads constructor(
    context: Context,
    private val attrs: AttributeSet? = null
) : ConstraintLayout(context, attrs) {

    private val binding = viewBinding(CustomSearchBarInputLayoutBinding::inflate)

    private val editText get() = binding.textInputEditText

    var text: String
        get() = editText.text.toString()
        set(value) {
            editText.apply {
                setText(value)
                setSelection(length())
            }
        }

    var hint: String?
        get() = binding.textInputLayout.hint.toString()
        set(value) {
            binding.textInputEditText.hint = value
        }

    private var isSingleLine by Delegates.observable(false) { _, _, newValue ->
        editText.isSingleLine = newValue
    }

    private var maxCharacter by Delegates.observable(-1) { _, _, newValue ->
        if (newValue != -1) {
            with(editText) {
                val newFilters = filters.toMutableList()
                newFilters.add(InputFilter.LengthFilter(newValue))
                filters = newFilters.toTypedArray()
            }
        }
    }

    init {
        loadAttrs()
        setOnClearButtonClickListener { clearInput() }
    }

    private fun loadAttrs() {
        context.obtainStyledAttributes(attrs, R.styleable.CustomSearchBarInputLayout).use { attrs ->
            text = attrs.getString(R.styleable.CustomSearchBarInputLayout_searchBarText).orEmpty()
            hint = attrs.getString(R.styleable.CustomSearchBarInputLayout_searchBarHint).orEmpty()
            isSingleLine = attrs.getBoolean(R.styleable.CustomSearchBarInputLayout_searchBarSingleLine, false)
            maxCharacter = attrs.getInteger(R.styleable.CustomSearchBarInputLayout_searchBarMaxCharacter, -1)
        }
    }

    fun setOnTextChangeListener(listener: (text: String) -> Unit) {
        editText.addTextChangedListener {
            if (editText.hasFocus()) {
                binding.clearButton.isVisible = text.isNotEmpty()
                listener.invoke(it.toString())
            }
        }
    }

    private fun setOnClearButtonClickListener(callback: () -> Unit) {
        binding.clearButton.setOnClickListener {
            callback.invoke()
        }
    }

    fun clearInput() {
        text = ""
        binding.clearButton.hide()
    }
}
