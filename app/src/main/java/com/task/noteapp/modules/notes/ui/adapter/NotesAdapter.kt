package com.task.noteapp.modules.notes.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.task.noteapp.modules.core.ui.viewholder.BaseDiffUtil
import com.task.noteapp.modules.core.ui.viewholder.BaseViewHolder
import com.task.noteapp.modules.notes.ui.model.BaseNoteCardListItem
import com.task.noteapp.modules.notes.ui.model.BaseNoteCardListItem.ItemType.ADD_NOTE_CARD
import com.task.noteapp.modules.notes.ui.model.BaseNoteCardListItem.ItemType.NOTE_CARD
import com.task.noteapp.modules.notes.ui.viewholder.AddNoteCardItemViewHolder
import com.task.noteapp.modules.notes.ui.viewholder.NoteCardItemViewHolder

class NotesAdapter(private val listener: NotesAdapterListener) :
    ListAdapter<BaseNoteCardListItem, BaseViewHolder<BaseNoteCardListItem>>(BaseDiffUtil<BaseNoteCardListItem>()) {

    private val noteCardItemListener = NoteCardItemViewHolder.NoteCardItemListener { noteId ->
        listener.onNoteClicked(noteId)
    }

    private val addNoteCardItemListener = AddNoteCardItemViewHolder.AddNoteCardItemListener {
        listener.onAddNoteClicked()
    }

    override fun getItemViewType(position: Int): Int {
        return getItem(position).itemType.ordinal
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<BaseNoteCardListItem> {
        return when (viewType) {
            NOTE_CARD.ordinal -> NoteCardItemViewHolder.create(parent, noteCardItemListener)
            ADD_NOTE_CARD.ordinal -> AddNoteCardItemViewHolder.create(parent, addNoteCardItemListener)
            else -> throw Exception("$logTag: Item View Type is Unknown.")
        }

    }

    override fun onBindViewHolder(holder: BaseViewHolder<BaseNoteCardListItem>, position: Int) {
        holder.bind(getItem(position))
    }

    interface NotesAdapterListener {
        fun onNoteClicked(noteId: Int)
        fun onAddNoteClicked()
    }

    companion object {
        private val logTag = NotesAdapter::class.java.simpleName
    }
}
