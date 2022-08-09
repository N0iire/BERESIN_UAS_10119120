package com.example.beresin_uas_10119120;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.beresin_uas_10119120.Adapter.NotesAdapter;
import com.example.beresin_uas_10119120.Database.NoteDatabase;
import com.example.beresin_uas_10119120.Model.Notes;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NotesTaker extends AppCompatActivity {
    EditText editText_title, editText_notes;
    ImageView imageView_save;
    List<Notes> list_notes;
    Notes notes;
    Spinner spinner;
    NotesAdapter adapter;
    boolean isOldNotes = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_taker);

        NoteDatabase db = new NoteDatabase(this);

        imageView_save = findViewById(R.id.imageView_save);
        editText_title = findViewById(R.id.editText_title);
        editText_notes = findViewById(R.id.editText_notes);

        notes = new Notes();
        try {
            notes = (Notes) getIntent().getSerializableExtra("old_note");
            editText_title.setText(notes.getTitle());
            editText_notes.setText(notes.getContent());
            isOldNotes = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        //spinner
        spinner = findViewById(R.id.spinner);
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("📝 Catatan");
        arrayList.add("🎓 Kuliah");
        arrayList.add("🏠 Rumah");
        arrayList.add("🔏 Pribadi");
        arrayList.add("❗ Penting");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrayList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String tutorialsName = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        imageView_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = editText_title.getText().toString();
                String description = editText_notes.getText().toString();
                String category = spinner.getSelectedItem().toString();

                if (description.isEmpty()) {
                    Toast.makeText(NotesTaker.this, "Please add some text", Toast.LENGTH_SHORT).show();
                    return;
                }
                SimpleDateFormat formatter = new SimpleDateFormat("EEE, d MM yyyy HH:mm a");
                Date date = new Date();

                if (!isOldNotes) {
                    notes = new Notes();
                }

                notes.setTitle(title);
                notes.setContent(description);
                notes.setDate(formatter.format(date));
                notes.setCategory(category);
                db.addNote(notes);
                list_notes.clear();
                list_notes.addAll(db.getNotes());
                adapter.notifyDataSetChanged();
                finish();
            }
        });
    }
}