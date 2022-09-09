package com.tareq.android.noteapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.tareq.android.noteapp.adapters.NotesAdapter;
import com.tareq.android.noteapp.entities.Notes;
import com.tareq.android.noteapp.viewmodel.NotesViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton newNotesBtn;
    NotesViewModel notesViewModel;
    RecyclerView NoteRv;
    NotesAdapter adapter;
    TextView nofilter, hightolow,lowtohigh;
    List<Notes> filternotesalllist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        newNotesBtn = findViewById(R.id.new_notesBtn);
        NoteRv = findViewById(R.id.NoteRv);

        nofilter = findViewById(R.id.noFilter);
        hightolow = findViewById(R.id.highTolow);
        lowtohigh = findViewById(R.id.lowtoHigh);

        nofilter.setBackgroundResource(R.drawable.filter_selected_sheep);

        nofilter.setOnClickListener(v -> {
            loadData(0);
            hightolow.setBackgroundResource(R.drawable.filter_un_sheep);
            lowtohigh.setBackgroundResource(R.drawable.filter_un_sheep);
            nofilter.setBackgroundResource(R.drawable.filter_selected_sheep);

        });

        hightolow.setOnClickListener(v -> {
            loadData(1);
            hightolow.setBackgroundResource(R.drawable.filter_selected_sheep);
            lowtohigh.setBackgroundResource(R.drawable.filter_un_sheep);
            nofilter.setBackgroundResource(R.drawable.filter_un_sheep);

        });

        lowtohigh.setOnClickListener(v -> {
            loadData(2);
            hightolow.setBackgroundResource(R.drawable.filter_un_sheep);
            lowtohigh.setBackgroundResource(R.drawable.filter_selected_sheep);
            nofilter.setBackgroundResource(R.drawable.filter_un_sheep);

        });

        notesViewModel = new ViewModelProvider(this)
                .get(NotesViewModel.class);

        newNotesBtn.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, InsertNotesActivity.class));
        });
//        notesViewModel.getAlNotes.observe(this,notes -> {
//
//         NoteRv.setLayoutManager(new GridLayoutManager(this,2));
//         adapter = new NotesAdapter(MainActivity.this,notes);
//         NoteRv.setAdapter(adapter);
//
//        });

    notesViewModel.getAlNotes.observe(this, new Observer<List<Notes>>() {
        @Override
        public void onChanged(List<Notes> notes) {
           setAdapter(notes);
            filternotesalllist = notes;
        }
    });

    }

    private void loadData(int i) {
        if (i==0){
            notesViewModel.getAlNotes.observe(this, new Observer<List<Notes>>() {
                @Override
                public void onChanged(List<Notes> notes) {
                    setAdapter(notes);
                    filternotesalllist = notes;
                }
            });
        }
        else if (i==1){
            notesViewModel.highToLow.observe(this, new Observer<List<Notes>>() {
                @Override
                public void onChanged(List<Notes> notes) {
                    setAdapter(notes);
                    filternotesalllist = notes;
                }
            });
        }
        else if (i==2){
            notesViewModel.lowToHigh.observe(this, new Observer<List<Notes>>() {
                @Override
                public void onChanged(List<Notes> notes) {
                    setAdapter(notes);
                    filternotesalllist = notes;
                }
            });
        }
    }

    public void setAdapter(List<Notes> notes){
        NoteRv.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
         adapter = new NotesAdapter(MainActivity.this,notes);
         NoteRv.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.surch_note,menu);

        MenuItem menuItem = menu.findItem(R.id.app_bar_search);

        SearchView searchView =(SearchView) menuItem.getActionView();
        searchView.setQueryHint("Search Notes Here...");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                NotesFilter(newText);

                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    private void NotesFilter(String newText) {

        ArrayList<Notes> FilterNames = new ArrayList<>();
        for ( Notes notes :this. filternotesalllist ){

            if(notes.notesTitle.contains(newText) || notes.noteSubtitle.contains(newText)){
                FilterNames.add(notes);
            }
        }
        this.adapter.searchNotes(FilterNames);

    }
}