package com.company;


import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Article {
    private String category;
    private String title;
    private String body;
    private String date;
    private boolean published;  // wenn true = keine Bearbeitung möglich
    private boolean favorite;
    private ArrayList<String> commentary;

    Article () {

    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getDate() {
        return date;
    }

    public void setDate() {
        this.date = Date.valueOf(LocalDate.now()).toString() + " " + Time.valueOf(LocalTime.now()).toString();
    }

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public void showCommentary() {
        for (String i : commentary) {
            System.out.println(i);
        }
    }

    public void addCommentary(String commentary) {
        this.commentary.add(commentary);
    }
}
