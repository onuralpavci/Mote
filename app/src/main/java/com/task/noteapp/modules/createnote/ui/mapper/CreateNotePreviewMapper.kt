package com.task.noteapp.modules.createnote.ui.mapper

import com.task.noteapp.modules.createnote.ui.model.BaseCreateNoteListItem
import com.task.noteapp.modules.createnote.ui.model.CreateNotePreview
import com.task.noteapp.modules.notes.ui.model.BaseNoteDate
import com.task.noteapp.utils.model.Event
import javax.inject.Inject

class CreateNotePreviewMapper @Inject constructor() {

    fun mapTo(
        id: Int,
        createNoteListItems: List<BaseCreateNoteListItem>,
        createDate: BaseNoteDate.CreateDate,
        updateDate: BaseNoteDate.UpdateDate?,
        isSaved: Boolean
    ): CreateNotePreview {
        return CreateNotePreview(
            noteId = id,
            createNoteListItems = createNoteListItems,
            createDate = createDate,
            updateDate = updateDate,
            openChatGPTEvent = null,
            navBackEvent = null,
            isSaved = isSaved,
            showSaveSuccessDialogEvent = null
        )
    }

    fun mapToErrorPreview(): CreateNotePreview {
        return CreateNotePreview(
            noteId = -1,
            createNoteListItems = emptyList(),
            createDate = BaseNoteDate.CreateDate.Yesterday,
            updateDate = null,
            openChatGPTEvent = null,
            navBackEvent = Event(Unit),
            isSaved = false,
            showSaveSuccessDialogEvent = null
        )
    }

    fun mapToInitialPreview(): CreateNotePreview {
        return CreateNotePreview(
            noteId = -1,
            createNoteListItems = emptyList(),
            createDate = BaseNoteDate.CreateDate.Yesterday,
            updateDate = null,
            openChatGPTEvent = null,
            navBackEvent = null,
            isSaved = false,
            showSaveSuccessDialogEvent = null
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
}
