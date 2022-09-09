package com.tareq.android.noteapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.tareq.android.noteapp.databinding.ActivityUpdateNotesActivituBinding;
import com.tareq.android.noteapp.entities.Notes;
import com.tareq.android.noteapp.viewmodel.NotesViewModel;

import java.util.Date;

public class UpdateNotesActivity extends AppCompatActivity {
    ActivityUpdateNotesActivituBinding binding;
    String priority = "1";
    String  stitle,ssubtitle,snotes,spriority;
    NotesViewModel notesViewModel;
    int iid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUpdateNotesActivituBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        notesViewModel =new ViewModelProvider(this)
                .get(NotesViewModel.class);

        iid = getIntent().getIntExtra("id",0);
        stitle = getIntent().getStringExtra("title");
        ssubtitle = getIntent().getStringExtra("subtitle");
       snotes = getIntent().getStringExtra("note");
        spriority = getIntent().getStringExtra("priority");

        binding.upTitle.setText(stitle);
        binding.upSubtitle.setText(ssubtitle);
        binding.upNotes.setText(snotes);

        if (spriority.equals("1")){
            binding.greenPy.setImageResource(R.drawable.ic_done_24);
        }
        else if (spriority.equals("2")){
            binding.yellowPy.setImageResource(R.drawable.ic_done_24);
        }
        else if (spriority.equals("3")){
            binding.redPy.setImageResource(R.drawable.ic_done_24);
        }

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


        binding.updateNotesBtn.setOnClickListener(v -> {
         String  title = binding.upTitle.getText().toString();
         String  subtitle = binding.upSubtitle.getText().toString();
         String  notes = binding.upNotes.getText().toString();

            UpdatesNotes(title,subtitle,notes);
        });
    }

    private void UpdatesNotes(String title, String subtitle, String notes) {

        Date date = new Date();
        CharSequence sequence = DateFormat.format("MMMM d,yyyy",date.getTime());

        Notes updateNotes = new Notes();
        updateNotes.id=iid;
        updateNotes.notesTitle = title;
        updateNotes.noteSubtitle = subtitle;
        updateNotes.notes = notes;
        updateNotes.notesPriority = priority;
        updateNotes.notesDate = sequence.toString();

        notesViewModel.updateNote(updateNotes);

        Toast.makeText(this, "Notes Updates SuccessFully", Toast.LENGTH_SHORT).show();
        finish();

   }
   @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.delete_menue,menu);
        return true;
   }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId()==R.id.ic_delete){

            BottomSheetDialog sheetDialog = new
                    BottomSheetDialog(UpdateNotesActivity.this,R.style.BottomSheetStyle);

            View view = LayoutInflater.from(UpdateNotesActivity.this)
                    .inflate(R.layout.delete_bottom_sheet,(LinearLayout)
                            findViewById(R.id.bottomSheet));
            sheetDialog.setContentView(view);

            TextView yes,no;

            yes =view.findViewById(R.id.deleteYes);
            no =view.findViewById(R.id.deleteNo);

            yes.setOnClickListener(v -> {

                notesViewModel.deleteNote(iid);
                finish();
            });

            no.setOnClickListener(v -> {
              sheetDialog.dismiss();
            });

            sheetDialog.show();

        }
        return true;
    }
}