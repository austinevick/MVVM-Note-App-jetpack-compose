package com.example.composenoteapp.data

import androidx.room.*
import com.example.composenoteapp.model.Note
import com.example.composenoteapp.model.notesTable
import kotlinx.coroutines.flow.Flow


@Dao
interface NoteDatabaseDao {

    @Query("SELECT * from $notesTable")
    fun getNotes(): Flow<List<Note>>

    @Query("SELECT * from $notesTable where id =:id")
    suspend fun getNoteById(id:String):Note

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(note: Note)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(note: Note)

    @Query("DELETE from $notesTable")
    suspend fun deleteAll()

    @Upsert(entity = Note::class)
    suspend fun upsertNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)

}
