package com.task.noteapp.modules.customview.customtaskbar

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import com.task.noteapp.R
import com.task.noteapp.databinding.CustomTaskbarBinding
import com.task.noteapp.modules.notes.ui.model.BaseNoteDate
import com.task.noteapp.utils.getFormattedTime
import com.task.noteapp.utils.viewbinding.viewBinding

class CustomTaskbar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : LinearLayout(context, attrs) {

    private val binding = viewBinding(CustomTaskbarBinding::inflate)

    fun setContent(content: String) {
        binding.contentTextView.text = content
    }

    fun setContent(noteDate: BaseNoteDate) {
        val formattedDateTime = context.getFormattedTime(noteDate)
        binding.contentTextView.text = formattedDateTime
    }

    fun setOnBookmarkButtonClickListener(callback: (() -> Unit)) {
        binding.bookmarkButton.setOnClickListener { callback.invoke() }
    }

    fun setOnDeleteButtonClickListener(callback: (() -> Unit)) {
        binding.deleteButton.setOnClickListener { callback.invoke() }
    }

    fun setBookMarkChecked(isChecked: Boolean) {
        if (isChecked) {
            binding.bookmarkButton.setIconResource(R.drawable.ic_bookmark_filled)
        } else {
            binding.bookmarkButton.setIconResource(R.drawable.ic_bookmark)
        }
    }
}
