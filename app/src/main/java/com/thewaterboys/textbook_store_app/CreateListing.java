package com.thewaterboys.textbook_store_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.firestore.FirebaseFirestore;

public class CreateListing extends AppCompatActivity {

    boolean formError = false;
    boolean value = true;

    EditText Title;
    EditText Author;
    EditText ISBN;
    EditText Descript;
    EditText Price;
    EditText Subject;
    EditText Email;

    Button ListBook;

    private Button btnViewDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_listing);

        Title = findViewById(R.id.title);
        Author = findViewById(R.id.author);
        ISBN = findViewById(R.id.isbn);
        Descript = findViewById(R.id.description);
        Price = findViewById(R.id.price);
        Subject = findViewById(R.id.subject);
        Email = findViewById(R.id.email);

       ListBook.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
                listBook();
            }
        });


    }



    void  listBook() {


        //else {
        Intent myIntent = new Intent(CreateListing.this, ListingCreated.class);

        Bundle mybundle = new Bundle();

        mybundle.putString("TitleOut", Title.getText().toString());
        mybundle.putString("AuthorOut", Author.getText().toString());
        mybundle.putString("ISBNOut", ISBN.getText().toString());
        mybundle.putString("DescriptOut", Descript.getText().toString());
        mybundle.putString("PriceOut", Price.getText().toString());
        mybundle.putString("SubjectOut", Subject.getText().toString());
        mybundle.putString("EmailOut", Email.getText().toString());

        myIntent.putExtras(mybundle);

        startActivity(myIntent);


        //}
    }
}


