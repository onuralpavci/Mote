package com.task.noteapp.modules.createnote.ui.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import com.task.noteapp.databinding.ItemCreateNoteDividerBinding
import com.task.noteapp.modules.core.ui.viewholder.BaseViewHolder
import com.task.noteapp.modules.createnote.ui.model.BaseCreateNoteListItem

class CreateNoteDividerItemViewHolder(
    private val binding: ItemCreateNoteDividerBinding
) : BaseViewHolder<BaseCreateNoteListItem>(binding.root) {

    override fun bind(item: BaseCreateNoteListItem) {
        if (item !is BaseCreateNoteListItem.DividerItem) return
    }


    companion object {
        fun create(parent: ViewGroup): CreateNoteDividerItemViewHolder {
            val binding = ItemCreateNoteDividerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return CreateNoteDividerItemViewHolder(binding)
        }
    }
}
