package com.searchbywiki;

import java.util.*;
import com.google.gson.*;
import java.awt.*;
import java.net.*;

public class ParseJsonFile {
    public static void parseAndDisplayResults(JsonObject response){
        JsonArray searchResults = response.getAsJsonObject("query").getAsJsonArray("search");
        
        if (searchResults.size() == 0){
            System.out.println("Результатов не найдено.");
            return;
        }

        System.out.println("Найдено " + searchResults.size() + " результатов");
        for (int i = 0; i < searchResults.size(); i++){
            JsonObject result = searchResults.get(i).getAsJsonObject();
            String title = result.get("title").getAsString();
            int pageId = result.get("pageid").getAsInt();
            System.out.println((i + 1) + ". " + title + " (pageid: " + pageId + ")");
        }

        int pageId = getPageID(searchResults);
        openInBrowser(pageId);
    }

    public static int getPageID(JsonArray searchResults){
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите номер статьи для открытия: ");
        int choice = -1;
        try {
            if (sc.hasNextInt()) {
                choice = sc.nextInt();
            } else {
                System.out.println("Ошибка: Вы должны ввести число.");
                return -1;
            }
        } catch (Exception e) {
            System.out.println("Произошла ошибка: " + e.getMessage());
            return -1;
        } finally {
            sc.close();
        }
        if(choice > 0 && choice <= searchResults.size()){
            int pageId = searchResults.get(choice - 1).getAsJsonObject().get("pageid").getAsInt();
            return pageId;
        }
        else
        {
            System.out.println("Ошибка: Неккоректный ввод.");
            return -1;
        }
    }

    public static void openInBrowser(int pageId){
        if (pageId == -1){
            return;
        }
        String url = "https://ru.wikipedia.org/w/index.php?curid=" + pageId;
        try{
            Desktop desktop = Desktop.getDesktop();
            desktop.browse(new URL(url).toURI());
            System.out.println("Открывается статья: " + url);
        }
        catch (Exception e){
            System.out.println("Не удалось открыть браузер: " + e.getMessage());
        }
    }
}
