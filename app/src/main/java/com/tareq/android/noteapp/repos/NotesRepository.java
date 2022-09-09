package com.tareq.android.noteapp.repos;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.tareq.android.noteapp.dao.NotesDao;
import com.tareq.android.noteapp.db.NotesDataBase;
import com.tareq.android.noteapp.entities.Notes;

import java.util.List;

public class NotesRepository {

    public NotesDao notesDao;
    public LiveData<List<Notes>> getallNotes;

    public LiveData<List<Notes>> highToLow;
    public LiveData<List<Notes>> lowToHigh;




    public NotesRepository(Application application) {

        NotesDataBase dataBase = NotesDataBase.getDatabaseInstance(application);
        notesDao = dataBase.notesDao();
        getallNotes = notesDao.getallNotes();

        highToLow = notesDao.highToLow();
        lowToHigh = notesDao.lowToHigh();
    }

   public void insertNotes(Notes notes){
        notesDao.insertNotes(notes);
    }

   public void deleteNotes(int id){
        notesDao.deleteNotes(id);
    }

 public void updateNotes(Notes notes){
    notesDao.updateNotes(notes);
   }


}
