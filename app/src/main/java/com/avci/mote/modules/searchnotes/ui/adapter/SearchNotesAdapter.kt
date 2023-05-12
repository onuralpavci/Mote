package com.avci.mote.modules.searchnotes.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.avci.mote.modules.core.ui.viewholder.BaseDiffUtil
import com.avci.mote.modules.core.ui.viewholder.BaseViewHolder
import com.avci.mote.modules.notes.ui.model.BaseNoteCardListItem
import com.avci.mote.modules.notes.ui.model.BaseNoteCardListItem.NoteCardListItem
import com.avci.mote.modules.notes.ui.viewholder.NoteCardItemViewHolder

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
}
