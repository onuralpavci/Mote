package com.task.noteapp.modules.searchnotes.ui.mapper

import com.task.noteapp.modules.customview.customscreenstate.ui.model.ScreenStateConfiguration
import com.task.noteapp.modules.notes.ui.mapper.ScreenStateConfigurationMapper
import com.task.noteapp.modules.notes.ui.model.BaseNoteCardListItem
import com.task.noteapp.modules.searchnotes.ui.model.SearchNotesPreview
import javax.inject.Inject

class SearchNotesPreviewMapper @Inject constructor(
    private val screenStateConfigurationMapper: ScreenStateConfigurationMapper
) {
    fun mapTo(
        screenStateConfiguration: ScreenStateConfiguration?,
        noteList: List<BaseNoteCardListItem.NoteCardListItem>,
        isEmptyStateVisible: Boolean,
        query: String?
    ): SearchNotesPreview {
        return SearchNotesPreview(
            screenStateConfiguration = screenStateConfiguration,
            noteList = noteList,
            isEmptyStateVisible = isEmptyStateVisible,
            query = query
        )
    }

    fun mapToInitialPreview(): SearchNotesPreview {
        return SearchNotesPreview(
            screenStateConfiguration = screenStateConfigurationMapper.mapToStartSearchState(),
            noteList = emptyList(),
            isEmptyStateVisible = true,
            query = null
        )
    }
}
