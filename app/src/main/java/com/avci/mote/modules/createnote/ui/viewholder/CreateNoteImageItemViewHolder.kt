package com.avci.mote.modules.createnote.ui.viewholder

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import com.avci.mote.databinding.ItemCreateNoteImageBinding
import com.avci.mote.modules.core.ui.viewholder.BaseViewHolder
import com.avci.mote.modules.createnote.ui.model.BaseCreateNoteListItem
import com.avci.mote.modules.customview.customimageinput.ui.CustomImageInput
import com.avci.mote.modules.customview.delegation.draggableitemviewholderdelegation.DraggableItemViewHolderComponent
import com.avci.mote.modules.customview.delegation.draggableitemviewholderdelegation.DraggableItemViewHolderDelegate

class CreateNoteImageItemViewHolder(
    private val binding: ItemCreateNoteImageBinding,
    private val listener: CreateNoteImageItemListener
) : BaseViewHolder<BaseCreateNoteListItem>(binding.root),
    DraggableItemViewHolderComponent<CustomImageInput> by DraggableItemViewHolderDelegate() {

    override fun bind(item: BaseCreateNoteListItem) {
        if (item !is BaseCreateNoteListItem.BaseNoteComponentItem.ImageItem) return
        binding.customImageLayout.apply {
            setOnSelectImageButtonClickListener {
                listener.onSelectImageButtonClicked(item.componentId)
            }
            setOnDownloadImageButtonClickListener {
                listener.onDownloadImageButtonClicked(item.componentId)
            }
            setOnDalleClickListener {
                listener.onDalleClickListener(item.componentId)
            }
            if (item.uri.isBlank()) {
                removeImage()
            } else {
                loadImage(
                    uri = Uri.parse(item.uri),
                    onLoadFailed = { listener.onLoadImageFailed(item.componentId) }
                )
            }
            setOnCloseClickListener {
                listener.onDeleteButtonClicked(item.componentId)
            }
            initDraggableItemViewHolderComponent(
                dragItemView = binding.customImageLayout,
                onDragged = { listener.onDragged(this@CreateNoteImageItemViewHolder) }
            )
        }
    }

    companion object {
        fun create(
            parent: ViewGroup,
            listener: CreateNoteImageItemListener
        ): CreateNoteImageItemViewHolder {
            val binding = ItemCreateNoteImageBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            return CreateNoteImageItemViewHolder(binding, listener)
        }
    }

    interface CreateNoteImageItemListener {
        fun onSelectImageButtonClicked(componentId: Int)
        fun onDownloadImageButtonClicked(componentId: Int)
        fun onDalleClickListener(componentId: Int)
        fun onLoadImageFailed(componentId: Int)
        fun onDeleteButtonClicked(componentId: Int)
        fun onDragged(viewHolder: CreateNoteImageItemViewHolder)
    }
}
