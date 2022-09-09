package com.tareq.android.noteapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Toast;

import com.tareq.android.noteapp.databinding.ActivityInsertNotesBinding;
import com.tareq.android.noteapp.entities.Notes;
import com.tareq.android.noteapp.viewmodel.NotesViewModel;

import java.util.Date;

public class InsertNotesActivity extends AppCompatActivity {

    ActivityInsertNotesBinding binding;
    String title,subtitle,notes;
    NotesViewModel notesViewModel;
    String priority = "1";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityInsertNotesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        notesViewModel =new  ViewModelProvider(this)
                .get(NotesViewModel.class);


        binding.greenPy.setOnClickListener(v -> {

            binding.greenPy.setImageResource(R.drawable.ic_done_24);
            binding.yellowPy.setImageResource(0);
            binding.redPy.setImageResource(0);
            priority = "1";
        });

        binding.yellowPy.setOnClickListener(v -> {
            binding.yellowPy.setImageResource(R.drawable.ic_done_24);
            binding.greenPy.setImageResource(0);
            binding.redPy.setImageResource(0);
            priority = "2";
        });

        binding.redPy.setOnClickListener(v -> {
            binding.greenPy.setImageResource(0);
            binding.yellowPy.setImageResource(0);
            binding.redPy.setImageResource(R.drawable.ic_done_24);
            priority = "3";
        });


        binding.doneNotesBtn.setOnClickListener(v -> {
            title = binding.notesTitle.getText().toString();
            subtitle = binding.notesSubtitle.getText().toString();
            notes = binding.notesData.getText().toString();

            CreateNotes(title,subtitle,notes);
        });
    }

    private void CreateNotes(String title, String subtitle, String notes) {

        Date date = new Date();
        CharSequence sequence = DateFormat.format("MMMM d,yyyy",date.getTime());


        Notes notes1 = new Notes();
        notes1.notesTitle = title;
        notes1.noteSubtitle = subtitle;
        notes1.notes = notes;
        notes1.notesPriority = priority;
        notes1.notesDate = sequence.toString();


        notesViewModel.insertNote(notes1);

        Toast.makeText(this, "Notes created SuccessFully", Toast.LENGTH_SHORT).show();
        finish();

    }
}