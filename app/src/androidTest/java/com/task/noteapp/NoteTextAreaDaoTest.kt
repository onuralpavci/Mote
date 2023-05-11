package com.task.noteapp

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.task.noteapp.database.NoteDatabase
import com.task.noteapp.database.dao.NoteDao
import com.task.noteapp.database.dao.NoteTextAreaDao
import com.task.noteapp.database.entity.NoteEntity
import com.task.noteapp.database.entity.NoteTextAreaEntity
import java.io.IOException
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import junit.framework.TestCase.assertNull
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NoteTextAreaDaoTest {
    private lateinit var noteTextAreaDao: NoteTextAreaDao
    private lateinit var db: NoteDatabase
    private lateinit var noteDao: NoteDao

    // variable to store the id of the inserted note
    private var noteId: Int = 0

    @Before
    fun createDb() {
        runBlocking {
            val context = ApplicationProvider.getApplicationContext<Context>()
            db = Room.inMemoryDatabaseBuilder(
                context, NoteDatabase::class.java
            ).build()
            noteTextAreaDao = db.noteTextAreaDao()
            noteDao = db.noteDao()

            // insert a note and store its id
            val note = NoteEntity("Title", 1000L, 2000L, false)
            noteId = noteDao.insertNote(note).toInt()
        }
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun insertAndGetNoteTextArea() = runBlocking {
        val noteTextArea = NoteTextAreaEntity("Text", 1, noteId)
        val newNoteTextAreaId = noteTextAreaDao.insertNoteTextArea(noteTextArea).toInt()
        val loaded = noteTextAreaDao.getNoteTextAreaByNoteId(noteId).firstOrNull { it.id == newNoteTextAreaId }
        assertNotNull(loaded)
        assertEquals(noteTextArea.copy(id = newNoteTextAreaId), loaded)
    }

    @Test
    @Throws(Exception::class)
    fun updateNoteTextAreaText() = runBlocking {
        val noteTextArea = NoteTextAreaEntity("Text", 1, noteId)
        val newNoteTextAreaId = noteTextAreaDao.insertNoteTextArea(noteTextArea).toInt()
        val newText = "Updated Text"
        noteTextAreaDao.updateTextAreaText(newNoteTextAreaId, newText)
        val updatedTextArea = noteTextAreaDao.getNoteTextAreaByNoteId(noteId).firstOrNull { it.id == newNoteTextAreaId }
        assertNotNull(updatedTextArea)
        assertEquals(newText, updatedTextArea?.text)
    }

    @Test
    @Throws(Exception::class)
    fun getAllNoteTextAreas() = runBlocking {
        val noteTextArea1 = NoteTextAreaEntity("Text1", 1, noteId)
        val noteTextArea2 = NoteTextAreaEntity("Text2", 2, noteId)
        val noteTextAreaId1 = noteTextAreaDao.insertNoteTextArea(noteTextArea1).toInt()
        val noteTextAreaId2 = noteTextAreaDao.insertNoteTextArea(noteTextArea2).toInt()
        val allNoteTextAreas = noteTextAreaDao.getAll()
        assertEquals(2, allNoteTextAreas.size)
        assertTrue(
            allNoteTextAreas.containsAll(
                listOf(
                    noteTextArea1.copy(id = noteTextAreaId1),
                    noteTextArea2.copy(id = noteTextAreaId2)
                )
            )
        )
    }

    @Test
    @Throws(Exception::class)
    fun deleteNoteTextArea() = runBlocking {
        val noteTextArea = NoteTextAreaEntity("Text", 1, noteId)
        val newNoteTextAreaId = noteTextAreaDao.insertNoteTextArea(noteTextArea).toInt()
        noteTextAreaDao.deleteNoteTextArea(newNoteTextAreaId)
        val loaded = noteTextAreaDao.getNoteTextAreaByNoteId(noteId).firstOrNull { it.id == newNoteTextAreaId }
        assertNull(loaded)
    }

    @Test
    @Throws(Exception::class)
    fun deleteAllNoteTextAreas() = runBlocking {
        val noteTextArea1 = NoteTextAreaEntity("Text1", 1, noteId)
        val noteTextArea2 = NoteTextAreaEntity("Text2", 2, noteId)
        noteTextAreaDao.insertNoteTextArea(noteTextArea1)
        noteTextAreaDao.insertNoteTextArea(noteTextArea2)
        noteTextAreaDao.deleteAllNoteTextAreas()
        val allNoteTextAreas = noteTextAreaDao.getAll()
        assertTrue(allNoteTextAreas.isEmpty())
    }

    @Test
    @Throws(Exception::class)
    fun getTextAreaHighestOrder() = runBlocking {
        val noteTextArea1 = NoteTextAreaEntity("Text1", 1, noteId)
        val noteTextArea2 = NoteTextAreaEntity("Text2", 2, noteId)
        noteTextAreaDao.insertNoteTextArea(noteTextArea1)
        noteTextAreaDao.insertNoteTextArea(noteTextArea2)
        val highestOrder = noteTextAreaDao.getTextAreaHighestOrder(noteId)
        assertEquals(2, highestOrder)
    }

}
