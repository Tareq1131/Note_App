package com.tareq.android.noteapp.db;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.tareq.android.noteapp.dao.NotesDao;
import com.tareq.android.noteapp.entities.Notes;


@Database(entities = {Notes.class},version = 1)
public abstract class NotesDataBase extends RoomDatabase {

    public abstract NotesDao notesDao();

    public static NotesDataBase INSTANCE;


    public static NotesDataBase getDatabaseInstance(Context context){

        if (INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    NotesDataBase.class,
                   "Notes_Database").allowMainThreadQueries().build();
        }
            return INSTANCE;
    }

}
