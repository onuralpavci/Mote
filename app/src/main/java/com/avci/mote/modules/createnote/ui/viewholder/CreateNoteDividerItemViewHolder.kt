package com.avci.mote.modules.createnote.ui.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import com.avci.mote.databinding.ItemCreateNoteDividerBinding
import com.avci.mote.modules.core.ui.viewholder.BaseViewHolder
import com.avci.mote.modules.createnote.ui.model.BaseCreateNoteListItem

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
