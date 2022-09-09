package com.tareq.android.noteapp.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.tareq.android.noteapp.entities.Notes;

import java.util.List;

@androidx.room.Dao
public interface NotesDao {

    @Query("Select * From Notes_Database")
    LiveData<List<Notes>> getallNotes();
   // List<Notes> getallNotes();

    @Query("Select * From Notes_Database ORDER BY notes_priority DESC")
    LiveData<List<Notes>> highToLow();

    @Query("Select * From Notes_Database ORDER BY notes_priority ASC")
    LiveData<List<Notes>> lowToHigh();


    @Insert
    void insertNotes(Notes...notes);

    @Query("DELETE FROM Notes_Database WHERE id=:id")
    void deleteNotes(int id);

    @Update
    void updateNotes(Notes notes);

}
