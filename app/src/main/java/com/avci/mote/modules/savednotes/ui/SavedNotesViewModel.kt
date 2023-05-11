package com.avci.mote.modules.savednotes.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.avci.mote.modules.savednotes.ui.model.SavedNotesPreview
import com.avci.mote.modules.savednotes.ui.usecase.SavedNotesPreviewUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@HiltViewModel
class SavedNotesViewModel @Inject constructor(
    private val savedNotesPreviewUseCase: SavedNotesPreviewUseCase
) : ViewModel() {

    private val _notesPreviewFlow = MutableStateFlow(savedNotesPreviewUseCase.getInitialPreview())
    val notesPreviewFlow: StateFlow<SavedNotesPreview>
        get() = _notesPreviewFlow

    init {
        viewModelScope.launch(Dispatchers.IO) {
            savedNotesPreviewUseCase.getSavedNotesPreviewFlow().collectLatest {
                _notesPreviewFlow.emit(it)
            }
        }
    }
}
