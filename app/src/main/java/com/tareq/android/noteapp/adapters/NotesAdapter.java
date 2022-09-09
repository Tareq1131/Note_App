package com.tareq.android.noteapp.adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tareq.android.noteapp.MainActivity;
import com.tareq.android.noteapp.R;
import com.tareq.android.noteapp.UpdateNotesActivity;
import com.tareq.android.noteapp.entities.Notes;

import java.util.ArrayList;
import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.notesViewHolder> {
    MainActivity mainActivity;
    List<Notes> notes;
    List<Notes> allNotesitem;

    public NotesAdapter(MainActivity mainActivity, List<Notes> notes) {
        this.mainActivity = mainActivity;
        this.notes = notes;
        allNotesitem = new ArrayList<>(notes);

    }

    public void searchNotes( List<Notes> filterredName){
         this.notes = filterredName;
         notifyDataSetChanged();
    }

    @NonNull
    @Override
    public notesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        return new notesViewHolder(LayoutInflater.from(mainActivity).inflate(R.layout.item_notes, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull notesViewHolder holder, int position) {

        Notes note = notes.get(position);
        holder.title.setText(note.notesTitle);
        holder.subtitle.setText(note.noteSubtitle);
        holder.notesData.setText(note.notesDate);

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(mainActivity, UpdateNotesActivity.class);
            intent.putExtra("id",note.id);
            intent.putExtra("title",note.notesTitle);
            intent.putExtra("subtitle",note.noteSubtitle);
            intent.putExtra("note",note.notes);
            intent.putExtra("priority",note.notesPriority);


           mainActivity.startActivity(intent);


        });
        //holder.notesData1.setText(note.notesDate1);

        if (note.notesPriority.equals("1")) {
            holder.notesPriority.setBackgroundResource(R.drawable.green_shape);
        }
        else if (note.notesPriority.equals("2")){
            holder.notesPriority.setBackgroundResource(R.drawable.yellow_shape);
        }
        else if (note.notesPriority.equals("3")) {
            holder.notesPriority.setBackgroundResource(R.drawable.red_shape);
        }
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    class notesViewHolder extends RecyclerView.ViewHolder {

        TextView title, subtitle, notesData,notesData1;
        View notesPriority;

        public notesViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.notesTitle);
            subtitle = itemView.findViewById(R.id.notesSubtitle);
            notesData = itemView.findViewById(R.id.notesDate);
            //notesData1 = itemView.findViewById(R.id.notesData1);
            notesPriority = itemView.findViewById(R.id.notesPriority);


        }
    }
}
