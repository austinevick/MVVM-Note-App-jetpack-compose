package com.example.composenoteapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.composenoteapp.common.DateConverter
import com.example.composenoteapp.common.UUIDConverter
import com.example.composenoteapp.model.Note

@Database(entities = [Note::class], version = 1, exportSchema = false)
@TypeConverters(DateConverter::class, UUIDConverter::class)
abstract class NoteDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDatabaseDao

    companion object{
        const val DATABASE_NAME = "notes_db"
    }
}