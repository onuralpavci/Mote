package com.task.noteapp.modules.notes.ui.model

import com.task.noteapp.modules.core.ui.model.RecyclerListItem

sealed class BaseNoteCardListItem : RecyclerListItem {

    abstract val itemType: ItemType

    enum class ItemType {
        NOTE_CARD,
        ADD_NOTE_CARD
    }

    data class NoteCardListItem(
        val noteId: Int,
        val title: String?,
        val imageUri: String?,
        val content: String?,
        val emptyTitleResId: Int,
        val emptyContentResId: Int,
        val createUpdateDate: BaseNoteDate
    ) : BaseNoteCardListItem() {

        override val itemType: ItemType
            get() = ItemType.NOTE_CARD

        override fun areItemsTheSame(other: RecyclerListItem): Boolean {
            return other is NoteCardListItem && noteId == other.noteId
        }

        override fun areContentsTheSame(other: RecyclerListItem): Boolean {
            return other is NoteCardListItem &&
                title == other.title &&
                imageUri == other.imageUri &&
                content == other.content
        }
    }

    object AddNoteCardListItem : BaseNoteCardListItem() {

        override val itemType: ItemType
            get() = ItemType.ADD_NOTE_CARD

        override fun areItemsTheSame(other: RecyclerListItem): Boolean {
            return other is AddNoteCardListItem
        }

        override fun areContentsTheSame(other: RecyclerListItem): Boolean {
            return other is AddNoteCardListItem
        }
    }
}
