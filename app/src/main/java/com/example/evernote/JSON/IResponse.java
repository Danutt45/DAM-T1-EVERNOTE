package com.example.evernote.JSON;

import com.example.evernote.notes.Note;

import java.util.List;

public interface IResponse {

     void onSucces(List<Note> _obj);
     void onError(String _error);
}
