package com.thewaterboys.textbook_store_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private Button btnViewDatabase;

    private DatabaseReference mDatabase;
    mDatabase = FirebaseDatabase.getInstance().getReference();

    private void writeNewBook(String Title, String Author, String ISBN) {
        Books book = new Books(Title, Author, ISBN);

        mDatabase.child("Books").child(userId).setValue(user);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        Button SignUpBtn = (Button) findViewById(R.id.SignUp);

        SignUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CreateAccount.class));

            }
        });
    }
}
