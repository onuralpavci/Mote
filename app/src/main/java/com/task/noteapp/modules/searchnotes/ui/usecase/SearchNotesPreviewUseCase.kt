package com.task.noteapp.modules.searchnotes.ui.usecase

import com.task.noteapp.modules.notes.ui.mapper.ScreenStateConfigurationMapper
import com.task.noteapp.modules.notes.ui.model.BaseNoteCardListItem
import com.task.noteapp.modules.notes.ui.usecase.GetNoteCardListItemFlowUseCase
import com.task.noteapp.modules.searchnotes.ui.mapper.SearchNotesPreviewMapper
import com.task.noteapp.modules.searchnotes.ui.model.SearchNotesPreview
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class SearchNotesPreviewUseCase @Inject constructor(
    private val getNoteCardListItemFlowUseCase: GetNoteCardListItemFlowUseCase,
    private val searchNotesPreviewMapper: SearchNotesPreviewMapper,
    private val screenStateConfigurationMapper: ScreenStateConfigurationMapper
) {
    suspend fun getNotesPreviewFlow(query: String?): Flow<SearchNotesPreview> {
        if (query == null || query.isBlank()) {
            return flow { emit(getInitialPreview()) }
        }
        return getNoteCardListItemFlowUseCase.invoke(query = query).map { noteCardListItem ->
            val filteredNoteListItems = noteCardListItem.filterIsInstance<BaseNoteCardListItem.NoteCardListItem>()
            val isEmptyStateVisible = filteredNoteListItems.isEmpty()
            val screenStateConfiguration = if (isEmptyStateVisible) {
                screenStateConfigurationMapper.mapToSearchEmptyNoteState()
            } else {
                null
            }
            searchNotesPreviewMapper.mapTo(
                screenStateConfiguration = screenStateConfiguration,
                noteList = filteredNoteListItems,
                isEmptyStateVisible = isEmptyStateVisible,
                query = query
            )
        }
    }

    fun getInitialPreview(): SearchNotesPreview {
        return searchNotesPreviewMapper.mapToInitialPreview()
    }
}
