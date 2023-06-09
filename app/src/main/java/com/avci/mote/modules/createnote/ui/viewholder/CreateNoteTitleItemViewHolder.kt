package com.avci.mote.modules.createnote.ui.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import com.avci.mote.databinding.ItemCreateNoteTitleBinding
import com.avci.mote.modules.core.ui.viewholder.BaseViewHolder
import com.avci.mote.modules.createnote.ui.model.BaseCreateNoteListItem

class CreateNoteTitleItemViewHolder(
    private val binding: ItemCreateNoteTitleBinding,
    private val listener: CreateNoteTitleItemListener
) : BaseViewHolder<BaseCreateNoteListItem>(binding.root) {

    override fun bind(item: BaseCreateNoteListItem) {
        if (item !is BaseCreateNoteListItem.TitleItem) return
        binding.customTitleInputLayout.apply {
            text = item.title
            setOnTextChangeListener {title ->
                item.title = title
                listener.onTitleChanged(title)
            }
        }
    }

    companion object {
        fun create(parent: ViewGroup, listener: CreateNoteTitleItemListener): CreateNoteTitleItemViewHolder {
            val binding = ItemCreateNoteTitleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return CreateNoteTitleItemViewHolder(binding, listener)
        }
    }

    fun interface CreateNoteTitleItemListener {
        fun onTitleChanged(title: String)
    }
}
