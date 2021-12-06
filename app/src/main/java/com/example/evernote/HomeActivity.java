package com.example.evernote;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.evernote.JSON.IResponse;
import com.example.evernote.JSON.JSONReader;
import com.example.evernote.database.Database;
import com.example.evernote.database.NoteDAO;
import com.example.evernote.notes.MenuAdaptor;
import com.example.evernote.notes.Note;
import com.example.evernote.userentity.Account;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class HomeActivity extends AppCompatActivity {


    private ListView listview;
    private MenuAdaptor menuAdaptor;
    private TextView name;
    private TextView date;
    private Account account;

    private NoteDAO noteDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_home);


        Intent intent = getIntent();
        account = (Account) intent.getParcelableExtra("AccountData");


        name = findViewById(R.id.tw_name);
        date = findViewById(R.id.tw_data);

        initHomeMessages();

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
                                for (Note l : _obj)
                                    _obj2.add(l);

                                menuAdaptor = new MenuAdaptor(_obj2);
                                listview.setAdapter(menuAdaptor);
                                int nr_elem = listview.getAdapter().getCount();
                                Log.v("nr_elem",String.valueOf(nr_elem));
                                Log.v("elem_adaptor",String.valueOf(menuAdaptor.getNote().size()));

                                InsertDB();


                            }
                        });
                    }

                    @Override
                    public void onError(String _error) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(HomeActivity.this, _error, Toast.LENGTH_SHORT);
                            }
                        });

                    }
                });
            }
        });
        th.start();



    }

    public void InsertDB(){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                noteDAO = Database.getInstance(getBaseContext()).getDataBase().noteDAO();
                int nr_elem = listview.getAdapter().getCount();
                for(int i = 0 ; i < nr_elem ; i++)
                    noteDAO.insert((Note)listview.getAdapter().getItem(i));
            }
        });
        thread.start();
    }

//TRB FACUTE un SYNC intre cele 2 threaduri !

    private List<Note> getNote() {
        List<Note> lst = new ArrayList<>();
        lst.add(new Note("Note 1", "Exemplu 0"));
        lst.add(new Note("Note 2", "Exemplu 1"));
        lst.add(new Note("Note 3", "Exemplu 2"));
        lst.add(new Note("Note 4", "Exemplu 3"));

        return lst;
    }

    private void initHomeMessages(){
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        int month =  calendar.get(Calendar.MONTH) ;
        int year = calendar.get(Calendar.YEAR);

        String date = getDay(day) + ", " + getMonth(month) + ", " + year;
        Toast.makeText(HomeActivity.this,String.valueOf(day),Toast.LENGTH_SHORT).show();
        name.setText("Hello, " + parseName(account.getMail()));
        this.date.setText(date);

    }

    private String getMonth(int month){
        String _month = "";
        switch(month){
            case 0: {_month ="January"; break;}
            case 1: {_month ="February"; break;}
            case 2: {_month ="March"; break;}
            case 3: {_month ="April"; break;}
            case 4: {_month ="May"; break;}
            case 5: {_month ="June"; break;}
            case 6: {_month ="July"; break;}
            case 7: {_month ="August"; break;}
            case 8: {_month ="September"; break;}
            case 9: {_month ="Octomber"; break;}
            case 10: {_month ="November"; break;}
            case 11: {_month ="December"; break;}
        }
        return _month;
    }

    private String getDay(int day){
        String _day = "";
        switch(day){
            case 2: {_day = "Monday"; break;}
            case 3: {_day = "Thuesday"; break;}
            case 4: {_day = "Wednesday"; break;}
            case 5: {_day = "Thursday"; break;}
            case 6: {_day = "Friday"; break;}
            case 7: {_day = "Saturday"; break;}
            case 1: {_day = "Sunday"; break;}

        }
        return _day;
    }

    private String parseName(String name){
        String[] _newName = name.split("@");
        _newName[0] = _newName[0].substring(0,1).toUpperCase() + _newName[0].substring(1).toLowerCase();
        return _newName[0];
    }

}