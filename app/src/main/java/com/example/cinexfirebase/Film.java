package com.example.cinexfirebase;

public class Film {

    String ID;
    String Title;
    String Time;

    public Film() {}

    public Film (String id, String title, String time) {
        this.ID = id;
        this.Title = title;
        this.Time = time;
    }

    public String getID() {
        return ID;
    }

    public String getTitle() {
        return Title;
    }

    public String getTime() {
        return Time;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public void setTime(String time) {
        Time = time;
    }
}
