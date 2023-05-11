package com.task.noteapp.modules.searchnotes.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.task.noteapp.modules.core.ui.viewholder.BaseDiffUtil
import com.task.noteapp.modules.core.ui.viewholder.BaseViewHolder
import com.task.noteapp.modules.notes.ui.model.BaseNoteCardListItem
import com.task.noteapp.modules.notes.ui.model.BaseNoteCardListItem.NoteCardListItem
import com.task.noteapp.modules.notes.ui.viewholder.NoteCardItemViewHolder

class SearchNotesAdapter(private val listener: SearchNotesAdapterListener) :
    ListAdapter<NoteCardListItem, BaseViewHolder<BaseNoteCardListItem>>(BaseDiffUtil<NoteCardListItem>()) {

    private val noteCardItemListener = NoteCardItemViewHolder.NoteCardItemListener { noteId ->
        listener.onNoteClicked(noteId)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<BaseNoteCardListItem> {
        return NoteCardItemViewHolder.create(parent, noteCardItemListener)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<BaseNoteCardListItem>, position: Int) {
        holder.bind(getItem(position))
    }

    fun interface SearchNotesAdapterListener {
        fun onNoteClicked(noteId: Int)
    }

    companion object {
        private val logTag = SearchNotesAdapter::class.java.simpleName
    }
}
