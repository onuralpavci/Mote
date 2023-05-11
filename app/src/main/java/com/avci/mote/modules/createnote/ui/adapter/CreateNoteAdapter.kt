package com.avci.mote.modules.createnote.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.avci.mote.modules.core.ui.viewholder.BaseDiffUtil
import com.avci.mote.modules.core.ui.viewholder.BaseViewHolder
import com.avci.mote.modules.createnote.ui.model.BaseCreateNoteListItem
import com.avci.mote.modules.createnote.ui.model.BaseCreateNoteListItem.ItemType.ADD_IMAGE_ACTION
import com.avci.mote.modules.createnote.ui.model.BaseCreateNoteListItem.ItemType.ADD_TEXT_AREA_ACTION
import com.avci.mote.modules.createnote.ui.model.BaseCreateNoteListItem.ItemType.ASK_CHATGPT_ACTION
import com.avci.mote.modules.createnote.ui.model.BaseCreateNoteListItem.ItemType.DIVIDER
import com.avci.mote.modules.createnote.ui.model.BaseCreateNoteListItem.ItemType.IMAGE
import com.avci.mote.modules.createnote.ui.model.BaseCreateNoteListItem.ItemType.LABEL
import com.avci.mote.modules.createnote.ui.model.BaseCreateNoteListItem.ItemType.TEXT_AREA
import com.avci.mote.modules.createnote.ui.model.BaseCreateNoteListItem.ItemType.TITLE
import com.avci.mote.modules.createnote.ui.viewholder.CreateNoteActionItemViewHolder
import com.avci.mote.modules.createnote.ui.viewholder.CreateNoteActionItemViewHolder.CreateNoteActionItemListener
import com.avci.mote.modules.createnote.ui.viewholder.CreateNoteDividerItemViewHolder
import com.avci.mote.modules.createnote.ui.viewholder.CreateNoteImageItemViewHolder
import com.avci.mote.modules.createnote.ui.viewholder.CreateNoteImageItemViewHolder.CreateNoteImageItemListener
import com.avci.mote.modules.createnote.ui.viewholder.CreateNoteLabelItemViewHolder
import com.avci.mote.modules.createnote.ui.viewholder.CreateNoteTextAreaItemViewHolder
import com.avci.mote.modules.createnote.ui.viewholder.CreateNoteTextAreaItemViewHolder.CreateNoteTextAreaItemListener
import com.avci.mote.modules.createnote.ui.viewholder.CreateNoteTitleItemViewHolder
import com.avci.mote.modules.createnote.ui.viewholder.CreateNoteTitleItemViewHolder.CreateNoteTitleItemListener

class CreateNoteAdapter(
    private val listener: CreateNoteAdapterListener
) :
    ListAdapter<BaseCreateNoteListItem, BaseViewHolder<BaseCreateNoteListItem>>(BaseDiffUtil<BaseCreateNoteListItem>()) {

    private val createNoteTitleItemListener = CreateNoteTitleItemListener {
        listener.onTitleChanged(it)
    }

    private val createNoteActionItemListener = CreateNoteActionItemListener {
        listener.onActionItemClicked(it)
    }

    private val createNoteTextAreaItemListener = object : CreateNoteTextAreaItemListener {
        override fun onTextChanged(text: String, componentId: Int) {
            listener.onTextAreaTextChanged(text = text, componentId = componentId)
        }

        override fun onEmptyTextDeleted(componentId: Int) {
            listener.onTextAreaEmptyTextDeleted(componentId)
        }

    }

    private val createNoteItemItemListener = object : CreateNoteImageItemListener {
        override fun onEditButtonClicked(componentId: Int) {
            listener.onImageEditButtonClicked(componentId)
        }

        override fun onLoadImageFailed(componentId: Int) {
            listener.onImageLoadImageFailed(componentId)
        }

        override fun onDeleteButtonClicked(componentId: Int) {
            listener.onImageDeleteButtonClicked(componentId)
        }

    }

    override fun getItemViewType(position: Int): Int {
        return getItem(position).itemType.ordinal
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<BaseCreateNoteListItem> {
        return when (viewType) {
            TITLE.ordinal -> CreateNoteTitleItemViewHolder.create(parent, createNoteTitleItemListener)
            LABEL.ordinal -> CreateNoteLabelItemViewHolder.create(parent)
            ADD_TEXT_AREA_ACTION.ordinal -> CreateNoteActionItemViewHolder.create(parent, createNoteActionItemListener)
            ADD_IMAGE_ACTION.ordinal -> CreateNoteActionItemViewHolder.create(parent, createNoteActionItemListener)
            ASK_CHATGPT_ACTION.ordinal -> CreateNoteActionItemViewHolder.create(parent, createNoteActionItemListener)
            TEXT_AREA.ordinal -> CreateNoteTextAreaItemViewHolder.create(parent, createNoteTextAreaItemListener)
            IMAGE.ordinal -> CreateNoteImageItemViewHolder.create(parent, createNoteItemItemListener)
            DIVIDER.ordinal -> CreateNoteDividerItemViewHolder.create(parent)
            else -> throw Exception("$logTag: Item View Type is Unknown.")
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder<BaseCreateNoteListItem>, position: Int) {
        holder.bind(getItem(position))
    }

    interface CreateNoteAdapterListener {
        fun onTitleChanged(title: String)
        fun onActionItemClicked(item: BaseCreateNoteListItem.BaseActionItem)
        fun onTextAreaTextChanged(text: String, componentId: Int)
        fun onTextAreaEmptyTextDeleted(componentId: Int)
        fun onImageEditButtonClicked(componentId: Int)
        fun onImageLoadImageFailed(componentId: Int)
        fun onImageDeleteButtonClicked(componentId: Int)
    }

    companion object {
        private val logTag = CreateNoteAdapter::class.java.simpleName
    }
}
