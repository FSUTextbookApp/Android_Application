package com.thewaterboys.textbook_store_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AccountCreated extends AppCompatActivity {

    Button list_book;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_created);


        EditText FirstNameOut = findViewById(R.id.firstName);
        EditText LastNameOut = findViewById(R.id.lastName);
        EditText emailOut = findViewById(R.id.email);
        EditText phoneOut = findViewById(R.id.phone);

        Intent myIntent = getIntent();

        Bundle mybundle = myIntent.getExtras();

        FirstNameOut.setText(mybundle.getString("FirstNameOut"));
        LastNameOut.setText(mybundle.getString("LastNameOut"));
        emailOut.setText(mybundle.getString("EmailOut"));
        phoneOut.setText(mybundle.getString("PhoneOut"));


        /*list_book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listBook();
            }
        });*/


    }

    /*void listBook() {

        Intent MyIntent = new Intent (AccountCreated.this, CreateListingActivity.class);

        startActivity(MyIntent);
    }*/
}
