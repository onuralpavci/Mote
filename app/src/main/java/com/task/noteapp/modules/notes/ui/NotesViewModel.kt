package com.task.noteapp.modules.notes.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.task.noteapp.modules.notes.ui.model.NotesPreview
import com.task.noteapp.modules.notes.ui.usecase.NotesPreviewUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val notesPreviewUseCase: NotesPreviewUseCase
) : ViewModel() {

    private val _notesPreviewFlow = MutableStateFlow(notesPreviewUseCase.getInitialPreview())
    val notesPreviewFlow: StateFlow<NotesPreview>
        get() = _notesPreviewFlow

    init {
        viewModelScope.launch(Dispatchers.IO) {
            notesPreviewUseCase.getNotesPreviewFlow().collectLatest {
                _notesPreviewFlow.emit(it)
            }
        }
    }
}
