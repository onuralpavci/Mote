package com.task.noteapp.modules.customview.customtextareainput.ui

import android.content.Context
import android.text.InputFilter
import android.util.AttributeSet
import android.view.KeyEvent
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.res.use
import androidx.core.widget.addTextChangedListener
import com.task.noteapp.R
import com.task.noteapp.databinding.CustomTextAreaInputLayoutBinding
import com.task.noteapp.utils.viewbinding.viewBinding
import kotlin.properties.Delegates

// TODO: Create a base class for CustomInputLayouts
class CustomTextAreaInputLayout @JvmOverloads constructor(
    context: Context,
    private val attrs: AttributeSet? = null
) : ConstraintLayout(context, attrs) {

    private val binding = viewBinding(CustomTextAreaInputLayoutBinding::inflate)

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
    }

    private fun loadAttrs() {
        context.obtainStyledAttributes(attrs, R.styleable.CustomTextAreaInputLayout).use { attrs ->
            text = attrs.getString(R.styleable.CustomTextAreaInputLayout_textAreaText).orEmpty()
            hint = attrs.getString(R.styleable.CustomTextAreaInputLayout_textAreaHint).orEmpty()
            isSingleLine = attrs.getBoolean(R.styleable.CustomTextAreaInputLayout_textAreaSingleLine, false)
            maxCharacter = attrs.getInteger(R.styleable.CustomTextAreaInputLayout_textAreaMaxCharacter, -1)
        }
    }

    fun setOnTextChangeListener(listener: (text: String) -> Unit) {
        editText.addTextChangedListener {
            if (editText.hasFocus()) {
                listener.invoke(it.toString())
            }
        }
    }

    fun setOnEmptyTextDeleteListener(callback: () -> Unit) {
        editText.setOnKeyListener { _, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_DEL && text.isEmpty()) {
                callback()
                true
            } else {
                false
            }
        }
    }
}
