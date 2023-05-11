package com.avci.mote.modules.savednotes.ui.usecase

import com.avci.mote.modules.notes.ui.mapper.ScreenStateConfigurationMapper
import com.avci.mote.modules.notes.ui.model.BaseNoteCardListItem
import com.avci.mote.modules.notes.ui.usecase.GetNoteCardListItemFlowUseCase
import com.avci.mote.modules.savednotes.ui.mapper.SavedNotesPreviewMapper
import com.avci.mote.modules.savednotes.ui.model.SavedNotesPreview
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SavedNotesPreviewUseCase @Inject constructor(
    private val getNoteCardListItemFlowUseCase: GetNoteCardListItemFlowUseCase,
    private val savedNotesPreviewMapper: SavedNotesPreviewMapper,
    private val screenStateConfigurationMapper: ScreenStateConfigurationMapper
) {
    suspend fun getSavedNotesPreviewFlow(): Flow<SavedNotesPreview> {
        return getNoteCardListItemFlowUseCase.invoke(filterSavedNotes = true).map { noteCardListItem ->
            val filteredNoteListItems = noteCardListItem.filterIsInstance<BaseNoteCardListItem.NoteCardListItem>()
            val isEmptyStateVisible = filteredNoteListItems.isEmpty()
            val screenStateConfiguration = if (isEmptyStateVisible) {
                screenStateConfigurationMapper.mapToNoSavedNoteState()
            } else {
                null
            }
            savedNotesPreviewMapper.mapTo(
                screenStateConfiguration = screenStateConfiguration,
                noteList = filteredNoteListItems,
                isEmptyStateVisible = isEmptyStateVisible
            )
        }
    }

    fun getInitialPreview(): SavedNotesPreview {
        return savedNotesPreviewMapper.mapToInitialPreview()
    }
}
