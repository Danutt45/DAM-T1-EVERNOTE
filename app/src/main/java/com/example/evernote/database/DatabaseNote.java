package com.example.evernote.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.evernote.notes.Note;

@Database(entities = {Note.class}, version = 1, exportSchema = false)
public abstract class DatabaseNote extends RoomDatabase {
    public abstract  NoteDAO noteDAO();
}
