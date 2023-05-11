package com.task.noteapp.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.task.noteapp.database.entity.NoteImageEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteImageDao {
    @Query("SELECT * FROM note_image_entity")
    suspend fun getAll(): List<NoteImageEntity>

    @Query("SELECT * FROM note_image_entity WHERE note_id = :noteId")
    suspend fun getNoteImagesByNoteId(noteId: Int): List<NoteImageEntity>

    @Query("SELECT * FROM note_image_entity WHERE note_id = :noteId")
    fun getNoteImagesByNoteIdAsFlow(noteId: Int): Flow<List<NoteImageEntity>>

    @Query("SELECT * FROM note_image_entity")
    fun getAllAsFlow(): Flow<List<NoteImageEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNoteImage(note: NoteImageEntity): Long

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateNoteImage(note: NoteImageEntity)

    @Query("DELETE FROM note_image_entity WHERE id = :noteImageId")
    suspend fun deleteNoteImage(noteImageId: Int)

    @Query("DELETE FROM note_image_entity")
    suspend fun deleteAllNoteImages()

    @Query("SELECT MAX(`order`) FROM note_image_entity WHERE note_id = :noteId")
    suspend fun getNoteImagesHighestOrder(noteId: Int): Int?

    @Query("UPDATE note_image_entity SET uri = :newUri WHERE id = :componentId")
    suspend fun updateNoteImageUri(componentId: Int, newUri: String?)
}
