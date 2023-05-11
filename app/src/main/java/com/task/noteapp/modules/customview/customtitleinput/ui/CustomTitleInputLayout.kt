package com.task.noteapp.modules.customview.customtitleinput.ui

import android.content.Context
import android.text.InputFilter
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.res.use
import androidx.core.widget.addTextChangedListener
import com.task.noteapp.R
import com.task.noteapp.databinding.CustomTitleInputLayoutBinding
import com.task.noteapp.utils.viewbinding.viewBinding
import kotlin.properties.Delegates

// TODO: Create a base class for CustomInputLayouts
class CustomTitleInputLayout @JvmOverloads constructor(
    context: Context,
    private val attrs: AttributeSet? = null
) : ConstraintLayout(context, attrs) {

    private val binding = viewBinding(CustomTitleInputLayoutBinding::inflate)

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
        context.obtainStyledAttributes(attrs, R.styleable.CustomTitleInputLayout).use { attrs ->
            text = attrs.getString(R.styleable.CustomTitleInputLayout_titleText).orEmpty()
            hint = attrs.getString(R.styleable.CustomTitleInputLayout_titleHint).orEmpty()
            isSingleLine = attrs.getBoolean(R.styleable.CustomTitleInputLayout_titleSingleLine, false)
            maxCharacter = attrs.getInteger(R.styleable.CustomTitleInputLayout_titleMaxCharacter, -1)
        }
    }

    fun setOnTextChangeListener(listener: (text: String) -> Unit) {
        editText.addTextChangedListener {
            listener.invoke(it.toString())
        }
    }
}
