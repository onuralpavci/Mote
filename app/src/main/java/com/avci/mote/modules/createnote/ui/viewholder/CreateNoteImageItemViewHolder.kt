package com.avci.mote.modules.createnote.ui.viewholder

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import com.avci.mote.databinding.ItemCreateNoteImageBinding
import com.avci.mote.modules.core.ui.viewholder.BaseViewHolder
import com.avci.mote.modules.createnote.ui.model.BaseCreateNoteListItem

class CreateNoteImageItemViewHolder(
    private val binding: ItemCreateNoteImageBinding,
    private val listener: CreateNoteImageItemListener
) : BaseViewHolder<BaseCreateNoteListItem>(binding.root) {

    override fun bind(item: BaseCreateNoteListItem) {
        if (item !is BaseCreateNoteListItem.BaseNoteComponentItem.ImageItem) return
        binding.customImageLayout.setOnEditButtonClickListener {
            listener.onEditButtonClicked(item.componentId)
        }
        if (item.uri.isBlank()) {
            binding.customImageLayout.removeImage()
        } else {
            binding.customImageLayout.loadImage(
                uri = Uri.parse(item.uri),
                onLoadFailed = { listener.onLoadImageFailed(item.componentId) }
            )
        }
        binding.customImageLayout.setOnCloseClickListener {
            listener.onDeleteButtonClicked(item.componentId)
        }
    }

    companion object {
        fun create(parent: ViewGroup, listener: CreateNoteImageItemListener): CreateNoteImageItemViewHolder {
            val binding = ItemCreateNoteImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return CreateNoteImageItemViewHolder(binding, listener)
        }
    }

    interface CreateNoteImageItemListener {
        fun onEditButtonClicked(componentId: Int)
        fun onLoadImageFailed(componentId: Int)
        fun onDeleteButtonClicked(componentId: Int)
    }
}
