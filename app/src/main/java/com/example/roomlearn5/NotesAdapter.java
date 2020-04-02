package com.example.roomlearn5;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NotesViewHolder> {

    private List<Note> notes;

    public NotesAdapter(List<Note> notes) {
        this.notes = notes;
    }

    //здесь устанавливаем layout note_item.xml c recyclerView
    @NonNull
    @Override
    public NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_item,parent,false);
        return new NotesViewHolder(view);
    }

    //в методе onBindViewHolder устанавливаем текст в textView
    //в зависимости что будет передано в getTitle() и getDescription()
    @Override
    public void onBindViewHolder(@NonNull NotesViewHolder holder, int position) {
        Note note = notes.get(position);
        holder.textViewTitle.setText(note.getTitle());
        holder.textViewDescription.setText(note.getDescription());
//        holder.textViewDayOfWeek.setText(note.getDayOfWeek());
        holder.textViewPriority.setText(String.format("%s",note.getPriority()));
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    //создаем внутренный класс и получаем textView из note_item.xml
    class NotesViewHolder extends RecyclerView.ViewHolder{

        private TextView textViewTitle;
        private TextView textViewDescription;
        private TextView textViewDayOfWeek;
        private TextView textViewPriority;

        //конструктор класса NotesViewHolder
        public NotesViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.tvTitle);
            textViewDescription = itemView.findViewById(R.id.tvDescription);
            textViewDayOfWeek = itemView.findViewById(R.id.tvDayOfWeek);
            textViewPriority = itemView.findViewById(R.id.tvPriority);
        }
    }
}
