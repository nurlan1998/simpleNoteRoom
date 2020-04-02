package com.example.roomlearn5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class AddNoteActivity extends AppCompatActivity {

    private EditText edTitle;
    private EditText edDescription;
    private Spinner spinnerDayOfWeek;
    private RadioGroup radioGroupPriority;
    NotesDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        edTitle = findViewById(R.id.edTitle);
        edDescription = findViewById(R.id.edDescription);
        spinnerDayOfWeek = findViewById(R.id.spinnerDayOfWeek);
        radioGroupPriority = findViewById(R.id.radioGroupPriority);
        database = NotesDatabase.getInstance(this);
    }

    public void onClickSaveNote(View view) {
        String title = edTitle.getText().toString().trim();
        String description = edDescription.getText().toString().trim();
        int dayOfWeek = spinnerDayOfWeek.getSelectedItemPosition();
        int radioButtonId = radioGroupPriority.getCheckedRadioButtonId();
        RadioButton radioButton = findViewById(radioButtonId);
        int priority = Integer.parseInt(radioButton.getText().toString());

        //проверяем если title и description не пустой то вставляем данные в базу и переходим в MainActivity
        if(isFilled(title,description)){
            Note note = new Note(title,description,dayOfWeek);
            database.notesDao().insertNote(note);
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
        }else {
            Toast.makeText(this, "Поля не заполнены", Toast.LENGTH_SHORT).show();
        }
    }

    //метод типа boolean для проверки не пустой ли title и description
    private boolean isFilled(String title, String description){
        return !title.isEmpty() && !description.isEmpty();
    }
}
