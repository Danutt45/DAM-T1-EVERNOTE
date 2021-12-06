package com.example.evernote.notes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.evernote.R;

import java.util.List;

public class MenuAdaptor extends BaseAdapter {

    List<Note> note;



    public MenuAdaptor(List<Note> note) {
        this.note = note;
    }

    public List<Note> getNote() {
        return note;
    }

    @Override
    public int getCount() {
        return note.size();
    }

    @Override
    public Object getItem(int position) {
        return (Note)note.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater infl = LayoutInflater.from(parent.getContext());
        View item = infl.inflate(R.layout.item_notite,parent,false);
        TextView t_titlu = item.findViewById(R.id.tw_titlu);
        TextView t_descriere = item.findViewById(R.id.tw_descriere);

        Note current = note.get(position);
        t_titlu.setText(current.getTitlu());
        t_descriere.setText(current.getDescriere());
        return item;
    }
}
