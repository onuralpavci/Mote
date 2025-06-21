package com.avci.mote.modules.createnote.ui.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import com.avci.mote.databinding.ItemCreateNoteTextAreaBinding
import com.avci.mote.modules.core.ui.viewholder.BaseViewHolder
import com.avci.mote.modules.createnote.ui.model.BaseCreateNoteListItem
import com.avci.mote.modules.customview.customtextareainput.ui.CustomTextInputLayout
import com.avci.mote.modules.customview.delegation.draggableitemviewholderdelegation.DraggableItemViewHolderComponent
import com.avci.mote.modules.customview.delegation.draggableitemviewholderdelegation.DraggableItemViewHolderDelegate

class CreateNoteTextAreaItemViewHolder(
    private val binding: ItemCreateNoteTextAreaBinding,
    private val listener: CreateNoteTextAreaItemListener
) : BaseViewHolder<BaseCreateNoteListItem>(binding.root),
    DraggableItemViewHolderComponent<CustomTextInputLayout> by DraggableItemViewHolderDelegate() {

    override fun bind(item: BaseCreateNoteListItem) {
        if (item !is BaseCreateNoteListItem.BaseNoteComponentItem.TextAreaItem) return
        binding.customTextAreaInputLayout.apply {
            text = item.text
            setOnTextChangeListener { text ->
                item.text = text
                listener.onTextChanged(text = text, componentId = item.componentId)
            }
            setOnEmptyTextDeleteListener {
                listener.onEmptyTextDeleted(componentId = item.componentId, adapterPosition = adapterPosition)
            }
            initDraggableItemViewHolderComponent(
                dragItemView = binding.customTextAreaInputLayout,
                onDragged = { listener.onDragged(this@CreateNoteTextAreaItemViewHolder) }
            )
        }
    }

    companion object {
        fun create(parent: ViewGroup, listener: CreateNoteTextAreaItemListener): CreateNoteTextAreaItemViewHolder {
            val binding = ItemCreateNoteTextAreaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return CreateNoteTextAreaItemViewHolder(binding, listener)
        }
    }

    interface CreateNoteTextAreaItemListener {
        fun onTextChanged(text: String, componentId: Int)
        fun onEmptyTextDeleted(componentId: Int, adapterPosition: Int)
        fun onDragged(viewHolder: CreateNoteTextAreaItemViewHolder)
    }
}
