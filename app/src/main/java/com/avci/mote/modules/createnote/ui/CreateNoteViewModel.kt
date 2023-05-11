package com.avci.mote.modules.createnote.ui

import android.net.Uri
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.avci.mote.modules.createnote.ui.model.BaseCreateNoteListItem
import com.avci.mote.modules.createnote.ui.model.CreateNotePreview
import com.avci.mote.modules.createnote.ui.usecase.CreateNotePreviewUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.launch

@HiltViewModel
class CreateNoteViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val createNotePreviewUseCase: CreateNotePreviewUseCase
) : ViewModel() {

    private val args = CreateNoteFragmentArgs.fromSavedStateHandle(savedStateHandle)

    private val _createNotePreviewFlow = MutableStateFlow(createNotePreviewUseCase.getInitialPreview())
    val createNotePreviewFlow: StateFlow<CreateNotePreview>
        get() = _createNotePreviewFlow

    private val titleInputFlow = MutableStateFlow<String?>(null)

    private val textAreaInputFlow = MutableStateFlow<Pair<Int, String>?>(null)

    private val isNewNote: Boolean
        get() = args.noteId == NEW_NOTE_ID

    var imageComponentIdToUpdate: Int? = null

    init {
        initPreviewFlow()
        initObservers()
    }

    fun onActionItemClicked(item: BaseCreateNoteListItem.BaseActionItem) {
        viewModelScope.launch(Dispatchers.IO) {
            createNotePreviewUseCase.updateNoteWithActionItem(
                noteId = getNoteId(),
                item = item
            )
            if (item is BaseCreateNoteListItem.BaseActionItem.AskChatGPTActionItem) {
                val openChatGPTEventPreview =
                    createNotePreviewUseCase.createOpenChatGPTEventPreview(_createNotePreviewFlow.value)
                _createNotePreviewFlow.emit(openChatGPTEventPreview)
            }
        }
    }

    fun onTitleChanged(title: String) {
        viewModelScope.launch(Dispatchers.IO) {
            titleInputFlow.emit(title)
        }
    }

    fun onTextAreaInputChanged(text: String, componentId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            textAreaInputFlow.emit(Pair(componentId, text))
        }
    }

    fun onTextAreaEmptyTextDeleted(componentId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            createNotePreviewUseCase.deleteNoteTextArea(
                noteId = args.noteId,
                componentId = componentId,
                shouldUpdateTime = isNewNote.not()
            )
        }
    }

    fun onImageDeleteClicked(componentId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            createNotePreviewUseCase.deleteNoteImage(
                noteId = args.noteId,
                componentId = componentId,
                shouldUpdateTime = isNewNote.not()
            )
        }
    }

    fun onImageSelected(uri: Uri) {
        val imageComponentId = imageComponentIdToUpdate ?: return
        viewModelScope.launch(Dispatchers.IO) {
            createNotePreviewUseCase.updateNoteImageUri(
                noteId = args.noteId,
                componentId = imageComponentId,
                uri = uri,
                shouldUpdateTime = isNewNote.not()
            )
        }
    }

    fun deleteNote() {
        viewModelScope.launch {
            createNotePreviewUseCase.deleteNote(getNoteId())
        }
    }

    fun updateIsnNoteSaved() {
        viewModelScope.launch {
            createNotePreviewUseCase.updateNoteIsSavedUseCase(getNoteId(), getIsSaved().not())
            if (getIsSaved().not()) {
                val showSuccessEventPreview =
                    createNotePreviewUseCase.createShowSaveSuccessDialogEventPreview(_createNotePreviewFlow.value)
                _createNotePreviewFlow.emit(showSuccessEventPreview)
            }
        }
    }

    private fun initPreviewFlow() {
        viewModelScope.launch(Dispatchers.IO) {
            val noteId = if (args.noteId == NEW_NOTE_ID) createNewNote() else args.noteId
            createNotePreviewUseCase.getCreateNotePreviewFlow(noteId).collectLatest {
                _createNotePreviewFlow.emit(it)
            }
        }
    }

    private fun initObservers() {
        viewModelScope.launch(Dispatchers.IO) {
            titleInputFlow
                .drop(1)
                .debounce(TITLE_INPUT_DEBOUNCE)
                .distinctUntilChanged()
                .collectLatest { title ->
                    updateTitle(title = title, noteId = getNoteId())
                }
        }

        viewModelScope.launch(Dispatchers.IO) {
            textAreaInputFlow
                .drop(1)
                .debounce(TEXT_AREA_INPUT_DEBOUNCE)
                .distinctUntilChanged()
                .collectLatest { idTextPair ->
                    idTextPair?.let {
                        val (componentId, text) = it
                        updateTextAreaInput(componentId = componentId, text = text)
                    }
                }
        }
    }

    private suspend fun createNewNote(): Int {
        return createNotePreviewUseCase.createNewNote()
    }

    private suspend fun updateTitle(title: String?, noteId: Int) {
        createNotePreviewUseCase.updateNoteTitle(
            noteId = noteId,
            newTitle = title,
            shouldUpdateTime = isNewNote.not()
        )
    }

    private suspend fun updateTextAreaInput(text: String?, componentId: Int) {
        createNotePreviewUseCase.updateNoteTextAreaText(
            noteId = args.noteId,
            componentId = componentId,
            newText = text,
            shouldUpdateTime = isNewNote.not()
        )
    }

    private fun getNoteId(): Int {
        return _createNotePreviewFlow.value.noteId
    }

    private fun getIsSaved(): Boolean {
        return _createNotePreviewFlow.value.isSaved
    }

    companion object {
        const val NEW_NOTE_ID = -1
        const val TITLE_INPUT_DEBOUNCE = 1000L
        const val TEXT_AREA_INPUT_DEBOUNCE = 1000L
    }
}
