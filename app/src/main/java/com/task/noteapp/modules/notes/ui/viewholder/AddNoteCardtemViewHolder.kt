package com.task.noteapp.modules.notes.ui.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import com.task.noteapp.databinding.ItemAddNoteCardBinding
import com.task.noteapp.modules.core.ui.viewholder.BaseViewHolder
import com.task.noteapp.modules.notes.ui.model.BaseNoteCardListItem

class AddNoteCardItemViewHolder(
    private val binding: ItemAddNoteCardBinding,
    private val listener: AddNoteCardItemListener
) : BaseViewHolder<BaseNoteCardListItem>(binding.root) {

    override fun bind(item: BaseNoteCardListItem) {
        if (item !is BaseNoteCardListItem.AddNoteCardListItem) return
        binding.root.setOnClickListener {
            listener.onAddNoteClicked()
        }
    }

    companion object {
        fun create(parent: ViewGroup, listener: AddNoteCardItemListener): AddNoteCardItemViewHolder {
            val binding = ItemAddNoteCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return AddNoteCardItemViewHolder(binding, listener)
        }
    }

    fun interface AddNoteCardItemListener {
        fun onAddNoteClicked()
    }
}
