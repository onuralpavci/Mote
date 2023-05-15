package com.avci.mote.modules.createnote.ui.mapper

import com.avci.mote.modules.createnote.ui.model.BaseCreateNoteListItem
import com.avci.mote.modules.createnote.ui.model.CreateNotePreview
import com.avci.mote.modules.notes.ui.model.BaseNoteDate
import com.avci.mote.utils.model.Event
import javax.inject.Inject

class CreateNotePreviewMapper @Inject constructor() {

    fun mapTo(
        id: Int,
        isNewNote: Boolean,
        createNoteListItems: List<BaseCreateNoteListItem>,
        createDate: BaseNoteDate.CreateDate,
        updateDate: BaseNoteDate.UpdateDate?,
        isSaved: Boolean
    ): CreateNotePreview {
        return CreateNotePreview(
            noteId = id,
            isNewNote = isNewNote,
            createNoteListItems = createNoteListItems,
            createDate = createDate,
            updateDate = updateDate,
            openChatGPTEvent = null,
            navBackEvent = null,
            isSaved = isSaved,
            showSaveSuccessDialogEvent = null,
            showDeleteEmptyNoteActionDialogEvent = null
        )
    }

    fun mapToErrorPreview(): CreateNotePreview {
        return CreateNotePreview(
            noteId = -1,
            isNewNote = false,
            createNoteListItems = emptyList(),
            createDate = BaseNoteDate.CreateDate.Yesterday,
            updateDate = null,
            openChatGPTEvent = null,
            navBackEvent = Event(Unit),
            isSaved = false,
            showSaveSuccessDialogEvent = null,
            showDeleteEmptyNoteActionDialogEvent = null
        )
    }

    fun mapToInitialPreview(): CreateNotePreview {
        return CreateNotePreview(
            noteId = -1,
            isNewNote = true,
            createNoteListItems = emptyList(),
            createDate = BaseNoteDate.CreateDate.Yesterday,
            updateDate = null,
            openChatGPTEvent = null,
            navBackEvent = null,
            isSaved = false,
            showSaveSuccessDialogEvent = null,
            showDeleteEmptyNoteActionDialogEvent = null
        )
    }

    fun mapToOpenChatGPTEventPreview(preview: CreateNotePreview): CreateNotePreview {
        return preview.copy(
            openChatGPTEvent = Event(Unit)
        )
    }

    fun mapToShowSaveSuccessDialogEventPreview(preview: CreateNotePreview): CreateNotePreview {
        return preview.copy(
            showSaveSuccessDialogEvent = Event(Unit)
        )
    }

    fun mapToNavBackEventPreview(preview: CreateNotePreview): CreateNotePreview {
        return preview.copy(
            navBackEvent = Event(Unit)
        )
    }

    fun mapToShowDeleteEmptyNoteEventPreview(preview: CreateNotePreview): CreateNotePreview {
        return preview.copy(
            showDeleteEmptyNoteActionDialogEvent = Event(Unit)
        )
    }
}
