package com.avci.mote.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.avci.mote.database.entity.NoteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {
    @Query("SELECT * FROM note_entity")
    suspend fun getAll(): List<NoteEntity>

    @Query("SELECT * FROM note_entity WHERE id = :noteId")
    suspend fun getNoteById(noteId: Int): NoteEntity?

    @Query("SELECT * FROM note_entity WHERE id = :noteId")
    fun getNoteByIdAsFlow(noteId: Int): Flow<NoteEntity?>

    @Query("SELECT * FROM note_entity")
    fun getAllAsFlow(): Flow<List<NoteEntity>>

    @Query("SELECT * FROM note_entity WHERE title LIKE '%' || :titleQuery || '%'")
    suspend fun getNotesWithTitleFiltered(titleQuery: String): List<NoteEntity>

    @Query("SELECT * FROM note_entity WHERE title LIKE '%' || :titleQuery || '%'")
    fun getNotesWithTitleFilteredAsFlow(titleQuery: String): Flow<List<NoteEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: NoteEntity): Long

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateNote(note: NoteEntity)

    @Query("DELETE FROM note_entity WHERE id = :noteId")
    suspend fun deleteNote(noteId: Int)

    @Query("DELETE FROM note_entity")
    suspend fun deleteAllNotes()

    @Query("UPDATE note_entity SET title = :newTitle WHERE id = :noteId")
    suspend fun updateNoteTitle(noteId: Int, newTitle: String?)

    @Query("UPDATE note_entity SET update_time_stamp = :lastEditTimeStamp WHERE id = :noteId")
    suspend fun updateNoteLastEditTimeStamp(noteId: Int, lastEditTimeStamp: Long?)

    @Query("UPDATE note_entity SET is_saved = :isSaved WHERE id = :noteId")
    suspend fun updateNoteIsSaved(noteId: Int, isSaved: Boolean)
}
