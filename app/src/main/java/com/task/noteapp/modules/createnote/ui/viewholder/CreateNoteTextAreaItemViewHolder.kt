package com.task.noteapp.modules.createnote.ui.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import com.task.noteapp.databinding.ItemCreateNoteTextAreaBinding
import com.task.noteapp.modules.core.ui.viewholder.BaseViewHolder
import com.task.noteapp.modules.createnote.ui.model.BaseCreateNoteListItem

class CreateNoteTextAreaItemViewHolder(
    private val binding: ItemCreateNoteTextAreaBinding,
    private val listener: CreateNoteTextAreaItemListener
) : BaseViewHolder<BaseCreateNoteListItem>(binding.root) {

    override fun bind(item: BaseCreateNoteListItem) {
        if (item !is BaseCreateNoteListItem.BaseNoteComponentItem.TextAreaItem) return
        binding.customTitleInputLayout.apply {
            text = item.text
            setOnTextChangeListener { text ->
                item.text = text
                listener.onTextChanged(text = text, componentId = item.componentId)
            }
            setOnEmptyTextDeleteListener {
                listener.onEmptyTextDeleted(componentId = item.componentId)
            }
        }
    }

    companion object {
        fun create(parent: ViewGroup, listener: CreateNoteTextAreaItemListener): CreateNoteTextAreaItemViewHolder {
            val binding = ItemCreateNoteTextAreaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return CreateNoteTextAreaItemViewHolder(binding, listener)
        }
    }

    interface CreateNoteTextAreaItemListener {
        fun onTextChanged(text: String, componentId: Int)
        fun onEmptyTextDeleted(componentId: Int)
    }
}
