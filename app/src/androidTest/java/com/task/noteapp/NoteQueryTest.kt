package com.task.noteapp

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.task.noteapp.modules.createnote.domain.model.BaseNoteComponent
import com.task.noteapp.modules.createnote.domain.model.Note
import com.task.noteapp.modules.notes.domain.usecase.DoesNoteContainQueryUseCase
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NoteQueryTest {

    private val doesNoteContainQueryUseCase = DoesNoteContainQueryUseCase()

    @Test
    fun doesNoteContainQuery_ShouldReturnTrue_WhenTitleContainsQuery() {
        val note = Note(
            id = 1,
            title = "This is a test note",
            createTimeStamp = System.currentTimeMillis(),
            updateTimeStamp = null,
            noteComponents = listOf(),
            isSaved = false
        )
        assertTrue(doesNoteContainQueryUseCase.invoke(note, "test"))
    }

    @Test
    fun doesNoteContainQuery_ShouldReturnTrue_WhenTextAreaComponentContainsQuery() {
        val note = Note(
            id = 1,
            title = null,
            createTimeStamp = System.currentTimeMillis(),
            updateTimeStamp = null,
            noteComponents = listOf(
                BaseNoteComponent.TextAreaComponent(
                    id = 1,
                    order = 1,
                    noteId = 1,
                    text = "This is a test note component"
                )
            ),
            isSaved = false
        )
        assertTrue(doesNoteContainQueryUseCase.invoke(note, "component"))
    }

    @Test
    fun doesNoteContainQuery_ShouldReturnFalse_WhenNeitherTitleNorTextAreaComponentContainsQuery() {
        val note = Note(
            id = 1,
            title = "This is a note",
            createTimeStamp = System.currentTimeMillis(),
            updateTimeStamp = null,
            noteComponents = listOf(
                BaseNoteComponent.TextAreaComponent(
                    id = 1,
                    order = 1,
                    noteId = 1,
                    text = "This is a note component"
                )
            ),
            isSaved = false
        )
        assertFalse(doesNoteContainQueryUseCase.invoke(note, "test"))
    }
}
