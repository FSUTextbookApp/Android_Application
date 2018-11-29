package com.thewaterboys.textbook_store_app;

import android.print.PageRange;

public class Note {
    private String title;
    private String description;
    //private int priority;
    private String ISBN;
    private String subject;
    private String price;
    private String sellersEmail;


    public Note() {
        //empty constructor needed
    }

    public Note(String title, String description, String ISBN, String subject, String price, String sellersEmail) { //took out int priority
        this.title = title;
        this.description = description;
        //this.priority = priority;
        this.ISBN = ISBN;
        this.subject = subject;
        this.price = price;
        this.sellersEmail = sellersEmail;

    }

    public String getSellersEmail() {
        return sellersEmail;
    }

    public String getTitle()
    {
        return title;
    }

    public String getDescription()
    {
        return description;
    }

    public String getISBN() {
        return ISBN;
    }

    public String getSubject() {
        return subject;
    }

    public String getPrice() {
        return price;
    }

    //    public int getPriority() {
//        return priority;
//    }
}