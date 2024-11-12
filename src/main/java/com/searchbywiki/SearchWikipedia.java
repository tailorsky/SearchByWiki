package com.searchbywiki;

import com.google.gson.*;
import java.io.*;
import java.net.*;

public class SearchWikipedia {
    public static JsonObject searchbywiki(String query){
        String urlString = "https://ru.wikipedia.org/w/api.php?action=query&list=search&utf8=&format=json&srsearch=" + query;

        System.out.println("Поиск по адресу: " + urlString);

        try{
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            InputStreamReader reader = new InputStreamReader(connection.getInputStream(), "UTF-8");
            JsonObject response = new Gson().fromJson(reader, JsonObject.class);
        
            reader.close();

            return response;
        }
        catch (IOException e){
            System.out.println("Ошибка запроса к серверу: " + e.getMessage());
        }

        return null;
    }
}
