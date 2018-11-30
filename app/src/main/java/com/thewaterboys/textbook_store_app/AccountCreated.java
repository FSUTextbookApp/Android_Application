package com.thewaterboys.textbook_store_app;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class AccountCreated extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private static final String TAG = "AccountCreated";

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

        if (mybundle != null) {
            FirstNameOut.setText(mybundle.getString("FirstNameOut"));
            LastNameOut.setText(mybundle.getString("LastNameOut"));
            emailOut.setText(mybundle.getString("EmailOut"));
            phoneOut.setText(mybundle.getString("PhoneOut"));
        }
    }
}
