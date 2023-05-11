package com.avci.mote.modules.notes.ui.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import com.avci.mote.databinding.ItemAddNoteCardBinding
import com.avci.mote.modules.core.ui.viewholder.BaseViewHolder
import com.avci.mote.modules.notes.ui.model.BaseNoteCardListItem

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
