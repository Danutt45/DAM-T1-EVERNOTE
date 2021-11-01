package com.example.evernote;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.TextView;

import com.example.evernote.notes.MenuAdaptor;
import com.example.evernote.notes.Note;
import com.example.evernote.userentity.Account;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {


    private ListView listview;
    private MenuAdaptor menuAdaptor;
    private TextView name;
    private Account account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_home);

        name = findViewById(R.id.tw_name);

        listview = findViewById(R.id.lw_notes);
        menuAdaptor = new MenuAdaptor(getNote());
        listview.setAdapter(menuAdaptor);

    }


    private List<Note> getNote() {
        List<Note> lst = new ArrayList<>();
        lst.add(new Note("Note 1", "Exemplu 0"));
        lst.add(new Note("Note 2", "Exemplu 1"));
        lst.add(new Note("Note 3", "Exemplu 2"));
        lst.add(new Note("Note 4", "Exemplu 3"));
        lst.add(new Note("Note 5", "Exemplu 4"));
        lst.add(new Note("Note 6", "Exemplu 5"));
        lst.add(new Note("Note 7", "Exemplu 6"));
        lst.add(new Note("Note 8", "Exemplu 7"));
        lst.add(new Note("Note 9", "Exemplu 8"));
        lst.add(new Note("Note 10", "Exemplu 9"));
        lst.add(new Note("Note 11", "Exemplu 10"));
        return lst;
    }
}