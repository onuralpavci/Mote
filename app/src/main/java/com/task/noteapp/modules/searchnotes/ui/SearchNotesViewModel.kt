package com.task.noteapp.modules.searchnotes.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.task.noteapp.modules.searchnotes.ui.model.SearchNotesPreview
import com.task.noteapp.modules.searchnotes.ui.usecase.SearchNotesPreviewUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch

@HiltViewModel
class SearchNotesViewModel @Inject constructor(
    private val searchNotesPreviewUseCase: SearchNotesPreviewUseCase
) : ViewModel() {
    private val _searchNotesPreviewFlow = MutableStateFlow(searchNotesPreviewUseCase.getInitialPreview())
    val searchNotesPreviewFlow: StateFlow<SearchNotesPreview>
        get() = _searchNotesPreviewFlow

    private val queryFlow = MutableStateFlow<String?>(null)

    init {
        initObservers()
    }

    fun updateQuery(query: String) {
        viewModelScope.launch {
            queryFlow.emit(query)
        }
    }

    private fun initObservers() {
        viewModelScope.launch(Dispatchers.IO) {
            queryFlow
                .debounce(QUERY_DEBOUNCE)
                .distinctUntilChanged()
                .flatMapLatest { query ->
                    searchNotesPreviewUseCase.getNotesPreviewFlow(query)
                }.collectLatest { list -> _searchNotesPreviewFlow.emit(list) }
        }
    }

    companion object {
        const val QUERY_DEBOUNCE = 400L
    }
}
