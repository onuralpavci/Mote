package com.task.noteapp.modules.notes.ui.usecase

import com.task.noteapp.modules.notes.ui.mapper.NoteCardListItemMapper
import com.task.noteapp.modules.notes.ui.mapper.NotesPreviewMapper
import com.task.noteapp.modules.notes.ui.mapper.ScreenStateConfigurationMapper
import com.task.noteapp.modules.notes.ui.model.NotesPreview
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class NotesPreviewUseCase @Inject constructor(
    private val getNoteCardListItemFlowUseCase: GetNoteCardListItemFlowUseCase,
    private val notesPreviewMapper: NotesPreviewMapper,
    private val screenStateConfigurationMapper: ScreenStateConfigurationMapper,
    private val noteCardListItemMapper: NoteCardListItemMapper
) {
    suspend fun getNotesPreviewFlow(): Flow<NotesPreview> {
        return getNoteCardListItemFlowUseCase.invoke().map { noteCardListItem ->
            val noteCardMutableList = noteCardListItem.toMutableList()
            val isEmptyStateVisible = noteCardMutableList.isEmpty()
            if (isEmptyStateVisible.not()) {
                val addNoteItem = noteCardListItemMapper.mapToAddNoteCardItem()
                noteCardMutableList.add(addNoteItem)
            }

            val screenStateConfiguration = if (isEmptyStateVisible) {
                screenStateConfigurationMapper.mapToEmptyNoteState()
            } else {
                null
            }
            notesPreviewMapper.mapTo(
                screenStateConfiguration = screenStateConfiguration,
                noteList = noteCardMutableList,
                isEmptyStateVisible = isEmptyStateVisible
            )
        }
    }

    fun getInitialPreview(): NotesPreview {
        return notesPreviewMapper.mapToInitialPreview()
    }
}
