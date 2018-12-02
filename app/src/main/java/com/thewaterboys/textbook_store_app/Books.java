package com.thewaterboys.textbook_store_app;

public class Books {

    public String title;
    public String author;
    public String ISBN;
    public String subject;
    public String description;
    public String price;
    public String sellerEmail;

    public Books() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public Books(String title, String author, String ISBN, String subject, String description, String price, String sellerEmail) {
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
        this.subject = subject;
        this.description = description;
        this.price = price;
        this.sellerEmail = sellerEmail;
    }

}