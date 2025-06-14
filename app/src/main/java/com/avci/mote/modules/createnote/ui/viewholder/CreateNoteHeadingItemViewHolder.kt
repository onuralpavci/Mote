package com.avci.mote.modules.createnote.ui.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import com.avci.mote.databinding.ItemCreateNoteHeadingBinding
import com.avci.mote.modules.core.ui.viewholder.BaseViewHolder
import com.avci.mote.modules.createnote.ui.model.BaseCreateNoteListItem
import com.avci.mote.modules.customview.customtextareainput.ui.CustomTextInputLayout
import com.avci.mote.modules.customview.delegation.draggableitemviewholderdelegation.DraggableItemViewHolderComponent
import com.avci.mote.modules.customview.delegation.draggableitemviewholderdelegation.DraggableItemViewHolderDelegate

class CreateNoteHeadingItemViewHolder(
    private val binding: ItemCreateNoteHeadingBinding,
    private val listener: CreateNoteHeadingItemListener
) : BaseViewHolder<BaseCreateNoteListItem>(binding.root),
    DraggableItemViewHolderComponent<CustomTextInputLayout> by DraggableItemViewHolderDelegate() {

    override fun bind(item: BaseCreateNoteListItem) {
        if (item !is BaseCreateNoteListItem.BaseNoteComponentItem.HeadingItem) return
        binding.customHeadingInputLayout.apply {
            text = item.text
            setOnTextChangeListener { text ->
                item.text = text
                listener.onTextChanged(text = text, componentId = item.componentId)
            }
            setOnEmptyTextDeleteListener {
                listener.onEmptyTextDeleted(componentId = item.componentId, adapterPosition = adapterPosition)
            }
            initDraggableItemViewHolderComponent(
                dragItemView = binding.customHeadingInputLayout,
                onDragged = { listener.onDragged(this@CreateNoteHeadingItemViewHolder) }
            )
        }
    }

    companion object {
        fun create(parent: ViewGroup, listener: CreateNoteHeadingItemListener): CreateNoteHeadingItemViewHolder {
            val binding = ItemCreateNoteHeadingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return CreateNoteHeadingItemViewHolder(binding, listener)
        }
    }

    interface CreateNoteHeadingItemListener {
        fun onTextChanged(text: String, componentId: Int)
        fun onEmptyTextDeleted(componentId: Int, adapterPosition: Int)
        fun onDragged(viewHolder: CreateNoteHeadingItemViewHolder)
    }
}
