package com.avci.mote.modules.notes.domain.usecase

import com.avci.mote.modules.createnote.domain.mapper.NoteMapper
import com.avci.mote.modules.createnote.domain.model.Note
import com.avci.mote.modules.notes.domain.repository.NotesRepository
import com.avci.mote.modules.notes.domain.repository.NotesRepository.Companion.NOTES_REPOSITORY_INJECTION_NAME
import javax.inject.Inject
import javax.inject.Named
import kotlin.math.max
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine

class GetNoteListFlowUseCase @Inject constructor(
    @Named(NOTES_REPOSITORY_INJECTION_NAME)
    private val notesRepository: NotesRepository,
    private val doesNoteContainQueryUseCase: DoesNoteContainQueryUseCase,
    private val noteMapper: NoteMapper
) {
    suspend operator fun invoke(query: String? = null, filterSavedNotes: Boolean = false): Flow<List<Note>> {
        val notesDTOFlow = notesRepository.getNotesDTOFlow()
        val noteComponentDTOFlow = notesRepository.getAllNoteComponentsDTOFlow()
        val combined = notesDTOFlow.combine(noteComponentDTOFlow) { noteList, noteComponentList ->
            val mappedNoteList = noteList.map { noteDTO ->
                val filteredComponents = noteComponentList.filter { it.noteId == noteDTO.id }
                noteMapper.mapTo(noteDTO, filteredComponents)
            }

            mappedNoteList.filter {
                (query == null || doesNoteContainQueryUseCase.invoke(it, query)) &&
                    (!filterSavedNotes || it.isSaved)
            }.sortedByDescending { max(it.updateTimeStamp ?: 0, it.createTimeStamp) }
        }
        return combined
    }
}
