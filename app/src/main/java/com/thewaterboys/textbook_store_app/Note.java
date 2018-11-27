package com.thewaterboys.textbook_store_app;

public class Note {
    private String title;
    private String description;
    //private int priority;
    private String ISBN;
    private String subject;
    private String price;


    public Note() {
        //empty constructor needed
    }

    public Note(String title, String description, String ISBN, String subject, String price) { //took out int priority
        this.title = title;
        this.description = description;
        //this.priority = priority;
        this.ISBN = ISBN;
        this.subject = subject;
        this.price = price;

    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

//    public int getPriority() {
//        return priority;
//    }
}