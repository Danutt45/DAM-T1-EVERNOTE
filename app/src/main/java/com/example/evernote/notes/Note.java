package com.example.evernote.notes;

import java.util.List;

public class Note{
    private String titlu;
    private String descriere;

    public Note(String titlu, String descriere) {
        this.titlu = titlu;
        this.descriere = descriere;
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

    public Note() {
    }

    @Override
    public String toString() {
        return "Note{" +
                "titlu='" + titlu + '\'' +
                ", descriere='" + descriere + '\'' +
                '}';
    }

}
