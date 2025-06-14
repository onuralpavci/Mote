package com.avci.mote.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.avci.mote.database.entity.NoteTextAreaEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteTextAreaDao {
    @Query("SELECT * FROM note_text_area_entity")
    suspend fun getAll(): List<NoteTextAreaEntity>

    @Query("SELECT * FROM note_text_area_entity WHERE note_id = :noteId")
    suspend fun getNoteTextAreaByNoteId(noteId: Int): List<NoteTextAreaEntity>

    @Query("SELECT * FROM note_text_area_entity WHERE note_id = :noteId")
    fun getNoteTextAreaByNoteIdAsFlow(noteId: Int): Flow<List<NoteTextAreaEntity>>

    @Query("SELECT * FROM note_text_area_entity")
    fun getAllAsFlow(): Flow<List<NoteTextAreaEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNoteTextArea(note: NoteTextAreaEntity): Long

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateNoteTextArea(note: NoteTextAreaEntity)

    @Query("DELETE FROM note_text_area_entity WHERE id = :noteTextAreaId")
    suspend fun deleteNoteTextArea(noteTextAreaId: Int)

    @Query("DELETE FROM note_text_area_entity")
    suspend fun deleteAllNoteTextAreas()

    @Query("SELECT MAX(`order`) FROM note_text_area_entity WHERE note_id = :noteId")
    suspend fun getTextAreaHighestOrder(noteId: Int): Int?

    @Query("UPDATE note_text_area_entity SET text = :newText WHERE id = :componentId")
    suspend fun updateTextAreaText(componentId: Int, newText: String?)

    @Query("UPDATE note_text_area_entity SET `order` = :newOrder WHERE id = :componentId")
    suspend fun updateTextAreaOrder(componentId: Int, newOrder: Int)
}
