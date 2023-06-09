package com.avci.mote.modules.createnote.ui.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import com.avci.mote.databinding.ItemCreateNoteLabelBinding
import com.avci.mote.modules.core.ui.viewholder.BaseViewHolder
import com.avci.mote.modules.createnote.ui.model.BaseCreateNoteListItem

class CreateNoteLabelItemViewHolder(
    private val binding: ItemCreateNoteLabelBinding
) : BaseViewHolder<BaseCreateNoteListItem>(binding.root) {

    override fun bind(item: BaseCreateNoteListItem) {
        if (item !is BaseCreateNoteListItem.LabelItem) return
        binding.labelTextView.apply {
            text = context.getString(item.labelResId)
        }
    }


    companion object {
        fun create(parent: ViewGroup): CreateNoteLabelItemViewHolder {
            val binding = ItemCreateNoteLabelBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return CreateNoteLabelItemViewHolder(binding)
        }
    }
}
