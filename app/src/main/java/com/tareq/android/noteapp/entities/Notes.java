package com.tareq.android.noteapp.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Notes_Database")
public class  Notes {

   @PrimaryKey(autoGenerate = true)
    public int id;

   @ColumnInfo(name = "notes_title")
   public String notesTitle;

    @ColumnInfo(name = "notes_subtitle")
  public String noteSubtitle;

    @ColumnInfo(name = "notes_date")
  public String notesDate;

    @ColumnInfo(name = "notes")
   public String notes;

    @ColumnInfo(name = "notes_priority")
 public String notesPriority;


}
