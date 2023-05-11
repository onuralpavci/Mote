package com.task.noteapp

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.task.noteapp.database.NoteDatabase
import com.task.noteapp.database.dao.NoteDao
import com.task.noteapp.database.entity.NoteEntity
import java.io.IOException
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNull
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NoteDaoTest {
    private lateinit var noteDao: NoteDao
    private lateinit var db: NoteDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, NoteDatabase::class.java
        ).build()
        noteDao = db.noteDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun insertAndGetNote() = runBlocking {
        val note = NoteEntity("Title", 1000L, 2000L, false)
        val newNotId = noteDao.insertNote(note).toInt()  // assuming insertNote returns the id of the inserted note
        val loaded = noteDao.getNoteById(newNotId)
        assertTrue(note.copy(id = newNotId) == loaded)
    }

    @Test
    @Throws(Exception::class)
    fun getAllNotes() = runBlocking {
        val note1 = NoteEntity("Title1", 1000L, 2000L, false)
        val note2 = NoteEntity("Title2", 3000L, 4000L, false)
        val newNoteId1 = noteDao.insertNote(note1).toInt()
        val newNoteId2 = noteDao.insertNote(note2).toInt()
        val allNotes = noteDao.getAll()
        assertEquals(2, allNotes.size)
        assertTrue(allNotes.contains(note1.copy(id = newNoteId1)))
        assertTrue(allNotes.contains(note2.copy(id = newNoteId2)))
    }

    @Test
    @Throws(Exception::class)
    fun deleteNote() = runBlocking {
        val note = NoteEntity("Title", 1000L, 2000L, false)
        val newNoteId = noteDao.insertNote(note).toInt()
        noteDao.deleteNote(newNoteId)
        val loaded = noteDao.getNoteById(newNoteId)
        assertNull(loaded)
    }

    @Test
    @Throws(Exception::class)
    fun deleteAllNotes() = runBlocking {
        val note1 = NoteEntity("Title1", 1000L, 2000L, false)
        val note2 = NoteEntity("Title2", 3000L, 4000L, false)
        noteDao.insertNote(note1)
        noteDao.insertNote(note2)
        noteDao.deleteAllNotes()
        val allNotes = noteDao.getAll()
        assertTrue(allNotes.isEmpty())
    }

    @Test
    @Throws(Exception::class)
    fun updateNoteTitle() = runBlocking {
        val note = NoteEntity("Title", 1000L, 2000L, false)
        val newNoteId = noteDao.insertNote(note).toInt()
        noteDao.updateNoteTitle(newNoteId, "Updated Title")
        val loaded = noteDao.getNoteById(newNoteId)
        assertEquals("Updated Title", loaded?.title)
    }

    @Test
    @Throws(Exception::class)
    fun updateNoteLastEditTimeStamp() = runBlocking {
        val note = NoteEntity("Title", 1000L, 2000L, false)
        val newNoteId = noteDao.insertNote(note).toInt()
        val newTimeStamp = 3000L
        noteDao.updateNoteLastEditTimeStamp(newNoteId, newTimeStamp)
        val loaded = noteDao.getNoteById(newNoteId)
        assertEquals(newTimeStamp, loaded?.updateTimeStamp)
    }

    @Test
    @Throws(Exception::class)
    fun updateNoteIsSaved() = runBlocking {
        val note = NoteEntity("Title", 1000L, 2000L, false)
        val newNoteId = noteDao.insertNote(note).toInt()
        noteDao.updateNoteIsSaved(newNoteId, true)
        val loaded = noteDao.getNoteById(newNoteId)
        assertEquals(true, loaded?.isSaved)
    }

    @Test
    @Throws(Exception::class)
    fun getNotesWithTitleFiltered() = runBlocking {
        val note1 = NoteEntity("Title1", 1000L, 2000L, false)
        val note2 = NoteEntity("Title2", 3000L, 4000L, false)
        noteDao.insertNote(note1)
        noteDao.insertNote(note2)
        val filteredNotes = noteDao.getNotesWithTitleFiltered("Title1")
        assertEquals(1, filteredNotes.size)
        assertEquals("Title1", filteredNotes[0].title)
    }
}
