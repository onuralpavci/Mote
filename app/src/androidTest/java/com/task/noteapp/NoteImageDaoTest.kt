package com.task.noteapp

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.task.noteapp.database.NoteDatabase
import com.task.noteapp.database.dao.NoteDao
import com.task.noteapp.database.dao.NoteImageDao
import com.task.noteapp.database.entity.NoteEntity
import com.task.noteapp.database.entity.NoteImageEntity
import java.io.IOException
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NoteImageDaoTest {
    private lateinit var noteImageDao: NoteImageDao
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
            noteImageDao = db.noteImageDao()
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
    fun insertAndGetNoteImage() = runBlocking {
        val noteImage = NoteImageEntity("uri", 1, noteId)
        val newNoteImageId = noteImageDao.insertNoteImage(noteImage).toInt()
        val loaded = noteImageDao.getNoteImagesByNoteId(noteId)
        assertTrue(loaded.contains(noteImage.copy(id = newNoteImageId)))
    }

    @Test
    @Throws(Exception::class)
    fun getAllNoteImages() = runBlocking {
        val noteImage1 = NoteImageEntity("uri1", 1, noteId)
        val noteImage2 = NoteImageEntity("uri2", 2, noteId)
        noteImageDao.insertNoteImage(noteImage1)
        noteImageDao.insertNoteImage(noteImage2)
        val allImages = noteImageDao.getAll()
        assertEquals(2, allImages.size)
        assertTrue(allImages.contains(noteImage1.copy(id = allImages[0].id)))
        assertTrue(allImages.contains(noteImage2.copy(id = allImages[1].id)))
    }

    @Test
    @Throws(Exception::class)
    fun deleteNoteImage() = runBlocking {
        val noteImage = NoteImageEntity("uri", 1, noteId)
        val newNoteImageId = noteImageDao.insertNoteImage(noteImage).toInt()
        noteImageDao.deleteNoteImage(newNoteImageId)
        val allImages = noteImageDao.getAll()
        assertTrue(allImages.isEmpty())
    }

    @Test
    @Throws(Exception::class)
    fun deleteAllNoteImages() = runBlocking {
        val noteImage1 = NoteImageEntity("uri1", 1, noteId)
        val noteImage2 = NoteImageEntity("uri2", 2, noteId)
        noteImageDao.insertNoteImage(noteImage1)
        noteImageDao.insertNoteImage(noteImage2)
        noteImageDao.deleteAllNoteImages()
        val allImages = noteImageDao.getAll()
        assertTrue(allImages.isEmpty())
    }

    @Test
    @Throws(Exception::class)
    fun getNoteImagesHighestOrder() = runBlocking {
        val noteImage1 = NoteImageEntity("uri1", 1, noteId)
        val noteImage2 = NoteImageEntity("uri2", 2, noteId)
        noteImageDao.insertNoteImage(noteImage1)
        noteImageDao.insertNoteImage(noteImage2)
        val highestOrder = noteImageDao.getNoteImagesHighestOrder(noteId)
        assertEquals(2, highestOrder)
    }

    @Test
    @Throws(Exception::class)
    fun updateNoteImageUri() = runBlocking {
        val noteImage = NoteImageEntity("uri", 1, noteId)
        val newNoteImageId = noteImageDao.insertNoteImage(noteImage).toInt()
        val newUri = "newUri"
        noteImageDao.updateNoteImageUri(newNoteImageId, newUri)
        val updatedImage = noteImageDao.getNoteImagesByNoteId(noteId).firstOrNull { it.id == newNoteImageId }
        assertNotNull(updatedImage)
        assertEquals(newUri, updatedImage?.uri)
    }
}
