package com.example.evernote;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.evernote.JSON.IResponse;
import com.example.evernote.JSON.JSONReader;
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

        JSONReader reader = new JSONReader();
        Thread th = new Thread(new Runnable() {
            @Override
            public void run() {
                reader.read("https://jsonkeeper.com/b/FT8F", new IResponse() {
                    @Override
                    public void onSucces(List<Note> _obj) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                List<Note> _obj2 = getNote();
                                for(Note l : _obj )
                                    _obj2.add(l);

                                menuAdaptor = new MenuAdaptor(_obj2);
                                listview.setAdapter(menuAdaptor);
                            }
                        });
                    }

                    @Override
                    public void onError(String _error) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(HomeActivity.this,_error,Toast.LENGTH_SHORT);
                            }
                        });

                    }
                });
            }
        });
    th.start();


    }


    private List<Note> getNote() {
        List<Note> lst = new ArrayList<>();
        lst.add(new Note("Note 1", "Exemplu 0"));
        lst.add(new Note("Note 2", "Exemplu 1"));
        lst.add(new Note("Note 3", "Exemplu 2"));
        lst.add(new Note("Note 4", "Exemplu 3"));

        return lst;
    }
}