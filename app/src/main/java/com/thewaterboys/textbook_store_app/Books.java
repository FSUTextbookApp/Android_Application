package com.thewaterboys.textbook_store_app;

import android.icu.util.ULocale;

import java.util.Locale;

public class Books {

    public String Title;
    public String Author;
    public String ISBN;
    public String Category;

    public Books() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public Books(String Title, String Author, String ISBN, String Category) {
        this.Title = Title;
        this.Author = Author;
        this.ISBN = ISBN;
        this.Category = Category;
    }

}