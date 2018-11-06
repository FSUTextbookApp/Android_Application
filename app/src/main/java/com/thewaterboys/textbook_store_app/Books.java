package com.thewaterboys.textbook_store_app;

public class Books {

    public String Title;
    public String Author;
    public String ISBN;

    public Books() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public Books(String Title, String Author, String ISBN) {
        this.Title = Title;
        this.Author = Author;
        this.ISBN = ISBN;
    }

}