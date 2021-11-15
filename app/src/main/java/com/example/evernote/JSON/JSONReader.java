package com.example.evernote.JSON;

import com.example.evernote.notes.Note;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class JSONReader {
    public void read(String urlPath, IResponse response){

        try{
            URL url = new URL(urlPath);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = connection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader reader = new BufferedReader(inputStreamReader);

            StringBuilder result = new StringBuilder();

            String line = "";

            while((line = reader.readLine()) != null){
                result.append(line);
            }

            reader.close();
            inputStreamReader.close();
            inputStream.close();

            List<Note> lista = parsare(result.toString());
            response.onSucces(lista);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private List<Note> parsare(String jsonString){
        List<Note> lst = new ArrayList<>();

        try{
            JSONObject jsonObject = new JSONObject(jsonString);
            JSONArray jsonArray = jsonObject.getJSONArray("notite");

            for(int i = 0 ; i < jsonArray.length(); i++){
                JSONObject currentObject = jsonArray.getJSONObject(i);
                lst.add(new Note(
                   currentObject.getString("titlu"),
                   currentObject.getString("descriere")
                ));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return lst;
    }
}
