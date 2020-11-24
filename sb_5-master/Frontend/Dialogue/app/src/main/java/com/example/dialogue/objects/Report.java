package com.example.dialogue.objects;

public class Report {
    int id;
    String user;
    String message;
    public Report(int id, String user, String message){
        this.id = id;
        this.message = message;
        this.user = user;
    }

    public String getMessage() {
        return message;
    }

    public String getUser() {
        return user;
    }

    public int getId() {
        return id;
    }
}
