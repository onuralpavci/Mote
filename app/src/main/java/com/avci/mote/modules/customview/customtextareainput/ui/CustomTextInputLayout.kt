package com.avci.mote.modules.customview.customtextareainput.ui

import android.content.Context
import android.text.InputFilter
import android.util.AttributeSet
import android.view.KeyEvent
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.res.use
import androidx.core.widget.addTextChangedListener
import com.avci.mote.R
import com.avci.mote.databinding.CustomTextAreaInputLayoutBinding
import com.avci.mote.modules.customview.delegation.draggableitemviewdelegation.DraggableItemViewComponent
import com.avci.mote.modules.customview.delegation.draggableitemviewdelegation.DraggableItemViewDelegate
import com.avci.mote.utils.viewbinding.viewBinding
import kotlin.properties.Delegates

class CustomTextInputLayout @JvmOverloads constructor(
    context: Context,
    private val attrs: AttributeSet? = null
) : ConstraintLayout(context, attrs),
    DraggableItemViewComponent by DraggableItemViewDelegate() {

    private val binding = viewBinding(CustomTextAreaInputLayoutBinding::inflate)

    private val editText get() = binding.textInputEditText

    init {
        initDraggableItemViewComponent(binding.dragButton)
    }

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

    private var textAppearance by Delegates.observable(-1) { _, _, newValue ->
        editText.setTextAppearance(newValue)
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
            textAppearance = attrs.getResourceId(R.styleable.CustomTextAreaInputLayout_textAreaTextAppearance, -1)
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
