package com.task.noteapp.modules.createnote.ui.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import com.task.noteapp.databinding.ItemCreateNoteActionBinding
import com.task.noteapp.modules.core.ui.viewholder.BaseViewHolder
import com.task.noteapp.modules.createnote.ui.model.BaseCreateNoteListItem

class CreateNoteActionItemViewHolder(
    private val binding: ItemCreateNoteActionBinding,
    private val listener: CreateNoteActionItemListener
) : BaseViewHolder<BaseCreateNoteListItem>(binding.root) {

    override fun bind(item: BaseCreateNoteListItem) {
        if (item !is BaseCreateNoteListItem.BaseActionItem) return
        binding.actionTextView.apply {
            text = context.getString(item.textResId)
            setCompoundDrawablesWithIntrinsicBounds(item.actionIconResId, 0, 0, 0)
            setOnClickListener { listener.onItemClicked(item) }
        }
    }

    fun interface CreateNoteActionItemListener {
        fun onItemClicked(item: BaseCreateNoteListItem.BaseActionItem)
    }

    companion object {
        fun create(parent: ViewGroup, listener: CreateNoteActionItemListener): CreateNoteActionItemViewHolder {
            val binding = ItemCreateNoteActionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return CreateNoteActionItemViewHolder(binding, listener)
        }
    }
}
