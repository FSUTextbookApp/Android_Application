package com.thewaterboys.textbook_store_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class AccountCreated extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_created);


        final EditText FirstNameOut = findViewById(R.id.firstName);
        final EditText LastNameOut = findViewById(R.id.lastName);
        final EditText emailOut = findViewById(R.id.email);
        final EditText phoneOut = findViewById(R.id.phone);

        Intent myIntent = getIntent();

        Bundle mybundle = myIntent.getExtras();

        FirstNameOut.setText(mybundle.getString("FirstNameOut"));
        LastNameOut.setText(mybundle.getString("LastNameOut"));
        emailOut.setText(mybundle.getString("EmailOut"));
        phoneOut.setText(mybundle.getString("PhoneOut"));

    }
}
