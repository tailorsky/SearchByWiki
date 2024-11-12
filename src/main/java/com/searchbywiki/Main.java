package com.searchbywiki;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Scanner;

import com.google.gson.JsonObject;

public class Main {
    public static void main(String[] args) {
        try{
            System.out.println("Введите запрос: ");
            Scanner sc = new Scanner(System.in);
            String query = sc.nextLine();
            String encodedQuery = URLEncoder.encode(query, "UTF-8");

            JsonObject response = SearchWikipedia.searchbywiki(encodedQuery);
            ParseJsonFile.parseAndDisplayResults(response);

            sc.close();
        }
        catch (UnsupportedEncodingException e){
            System.out.println("Ошибка кодирования URL: " + e.getMessage());
        }
    }
}