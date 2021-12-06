package com.example.evernote.database;

import android.content.Context;

import androidx.room.Room;

public class Database {

    private static Database database;
    private DatabaseNote databaseNote;

    private Database(Context context) {
        databaseNote = Room.databaseBuilder(
                context,
                DatabaseNote.class,
                "note-database")
                .build();
    }

    public static Database getInstance(Context context) {
        if (database == null) {
            database = new Database(context);
        }
        return database;
    }

    public DatabaseNote getDataBase() {
        return databaseNote;
    }
}
