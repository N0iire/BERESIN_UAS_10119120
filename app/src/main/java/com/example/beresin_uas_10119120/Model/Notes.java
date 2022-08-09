package com.example.beresin_uas_10119120.Model;

import java.io.Serializable;

public class Notes implements Serializable {

    private long ID;
    private String title;
    private String category;
    private String content;
    private String date;
    boolean pinned = false;

    public Notes(){ }

    public Notes(String title, String content, String category, String date, boolean pinned){
        this.title = title;
        this.content = content;
        this.category = category;
        this.date = date;
        this.pinned = pinned;
    }

    public Notes(long id, String title, String content, String category, String date, boolean pinned){
        this.ID = id;
        this.title = title;
        this.content = content;
        this.category = category;
        this.date = date;
        this.pinned = pinned;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isPinned() {
        return pinned;
    }

    public void setPinned(boolean pinned) {
        this.pinned = pinned;
    }
}
