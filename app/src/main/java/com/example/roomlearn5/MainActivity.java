package com.example.roomlearn5;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private NotesAdapter adapter;
    private NotesDatabase database;
    private final List<Note> notes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        database = NotesDatabase.getInstance(this);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.hide();
        }
        recyclerView = findViewById(R.id.recyclerView);
        getData();
        adapter = new NotesAdapter(notes);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }
//
//    private void remove(int position){
//        Note note = notes.get(position);
//        database.notesDao().deleteNote(note);
//        getData();
//        adapter.notifyDataSetChanged();
//    }

    //здесь получаем и отображаем все данные из баз данных
    private void getData(){
        List<Note> notesFromDB = database.notesDao().getAllNotes();
        notes.clear();
        notes.addAll(notesFromDB);
    }

    public void onClickAddNote(View view) {
        Intent intent = new Intent(this,AddNoteActivity.class);
        startActivity(intent);
    }
}
