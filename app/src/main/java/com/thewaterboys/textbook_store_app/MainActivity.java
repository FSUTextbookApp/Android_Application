package com.thewaterboys.textbook_store_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {

    private Button btnViewDatabase;

/*    private DatabaseReference mDatabase;

    private void writeNewBook(String Title, String Author, String ISBN) {
        Books book = new Books(Title, Author, ISBN);

        mDatabase.child("Books").child(Title).setValue(book);
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        Books newBook = new Books("Harry Potter", "JK", "123456789");
        ApiFuture<WriteResult> future = db.collection("Books").document("JK").set(newBook);

       /* mDatabase = FirebaseDatabase.getInstance().getReference();
        writeNewBook("Harry Potter", "J.K. Rowling", "123456789");*/



        Button SignUpBtn = (Button) findViewById(R.id.SignUp);

        SignUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CreateAccount.class));

            }
        });
    }
}
