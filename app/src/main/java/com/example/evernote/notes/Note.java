package com.example.evernote.notes;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity(tableName = "note")
public class Note {
    @PrimaryKey(autoGenerate = true)
    private int idNote;

    @ColumnInfo(name = "titlu_nota")
    private String titlu;

    @ColumnInfo(name = "descriere_nota")
    private String descriere;

    public Note(String titlu, String descriere) {
        this.titlu = titlu;
        this.descriere = descriere;
    }

    public Note() {
    }

    public int getIdNote() {
        return idNote;
    }

    public void setIdNote(int idNote) {
        this.idNote = idNote;
    }

    public String getTitlu() {
        return titlu;
    }

    public void setTitlu(String titlu) {
        this.titlu = titlu;
    }

    public String getDescriere() {
        return descriere;
    }

    public void setDescriere(String descriere) {
        this.descriere = descriere;
    }

    @Override
    public String toString() {
        return "Note{" +
                "titlu='" + titlu + '\'' +
                ", descriere='" + descriere + '\'' +
                '}';
    }

}
