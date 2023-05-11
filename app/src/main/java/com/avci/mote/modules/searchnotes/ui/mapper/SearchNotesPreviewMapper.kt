package com.avci.mote.modules.searchnotes.ui.mapper

import com.avci.mote.modules.customview.customscreenstate.ui.model.ScreenStateConfiguration
import com.avci.mote.modules.notes.ui.mapper.ScreenStateConfigurationMapper
import com.avci.mote.modules.notes.ui.model.BaseNoteCardListItem
import com.avci.mote.modules.searchnotes.ui.model.SearchNotesPreview
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
