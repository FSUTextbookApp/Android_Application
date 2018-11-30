package com.thewaterboys.textbook_store_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import com.google.firebase.firestore.FirebaseFirestore;


public class ListingCreated extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listing_created);


        final EditText TitleOut = findViewById(R.id.title);
        final EditText AuthorOut = findViewById(R.id.author);
        final EditText ISBNOut = findViewById(R.id.isbn);
        final EditText DescriptOut = findViewById(R.id.description);
        final EditText PriceOut = findViewById(R.id.price);
        final EditText SubjectOut = findViewById(R.id.subject);
        final EditText EmailOut = findViewById(R.id.email);



        Intent myIntent = getIntent();

        Bundle myBundle = myIntent.getExtras();

        TitleOut.setText(myBundle.getString("TitleOut"));
        AuthorOut.setText(myBundle.getString("AuthorOut"));
        ISBNOut.setText(myBundle.getString("ISBNOut"));
        DescriptOut.setText(myBundle.getString("DescriptOut"));
        PriceOut.setText(myBundle.getString("PriceOut"));
        SubjectOut.setText(myBundle.getString("SubjectOut"));
        EmailOut.setText(myBundle.getString("EmailOut"));

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        Books newBook = new Books(myBundle.getString("ISBNOut"), myBundle.getString("AuthorOut"),
                myBundle.getString("DescriptOut"), myBundle.getString("PriceOut"),
                myBundle.getString("EmailOut"), myBundle.getString("SubjectOut"), myBundle.getString("TitleOut"));

        db.collection("Books").add(newBook);



    }
}
