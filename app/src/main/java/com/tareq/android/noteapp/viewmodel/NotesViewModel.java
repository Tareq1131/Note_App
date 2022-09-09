package com.tareq.android.noteapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.tareq.android.noteapp.entities.Notes;
import com.tareq.android.noteapp.repos.NotesRepository;

import java.util.List;

public class NotesViewModel extends AndroidViewModel {

    public NotesRepository repository;
    public LiveData<List<Notes>> getAlNotes;
    public LiveData<List<Notes>> highToLow;
    public LiveData<List<Notes>> lowToHigh;

    public NotesViewModel(@NonNull Application application) {
        super(application);

          repository = new NotesRepository(application);
         getAlNotes = repository.getallNotes;

         highToLow = repository.highToLow;
         lowToHigh = repository.lowToHigh;

    }

  public void insertNote(Notes notes){
        repository.insertNotes(notes);
  }

  public void deleteNote(int id){
        repository.deleteNotes(id);
  }

 public void updateNote(Notes notes){
        repository.updateNotes(notes);
  }

}
