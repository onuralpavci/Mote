package com.task.noteapp.modules.notes.ui.mapper

import com.task.noteapp.R
import com.task.noteapp.modules.customview.customscreenstate.ui.model.ScreenStateConfiguration
import javax.inject.Inject

class ScreenStateConfigurationMapper @Inject constructor() {

    fun mapToEmptyNoteState(): ScreenStateConfiguration {
        return ScreenStateConfiguration(
            titleResId = R.string.start_your,
            startImageResId = R.drawable.ic_start_your_journey,
            descriptionResId = R.string.every_big
        )
    }

    fun mapToSearchEmptyNoteState(): ScreenStateConfiguration {
        return ScreenStateConfiguration(
            titleResId = R.string.oops_not_found,
            startImageResId = R.drawable.ic_woman_thinking,
            descriptionResId = R.string.sorry_we_can_t
        )
    }

    fun mapToStartSearchState(): ScreenStateConfiguration {
        return ScreenStateConfiguration(
            titleResId = R.string.searching_for_a,
            startImageResId = R.drawable.ic_standing_woman,
            descriptionResId = R.string.you_can_search
        )
    }

    fun mapToNoSavedNoteState(): ScreenStateConfiguration {
        return ScreenStateConfiguration(
            titleResId = R.string.no_saved,
            startImageResId = R.drawable.ic_looking_window,
            descriptionResId = R.string.once_you
        )
    }
}
