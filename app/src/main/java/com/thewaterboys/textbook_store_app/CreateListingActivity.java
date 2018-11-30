package com.thewaterboys.textbook_store_app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class CreateListingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_listing);

        final Spinner spinner = findViewById(R.id.spinner_subject);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.departments,R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        Button postListingBtn = (Button) findViewById(R.id.listBook);

        postListingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText bookTitle = findViewById(R.id.title);
                final EditText bookAuthor = findViewById(R.id.author);
                final EditText bookISBN = findViewById(R.id.isbn);
                final EditText bookDescription = findViewById(R.id.description);
                final EditText bookPrice = findViewById(R.id.price);
//                final EditText bookSubject = findViewById(R.id.subject);



                String title = bookTitle.getText().toString();
                String author = bookAuthor.getText().toString();
                String isbn = bookISBN.getText().toString();
                String description = bookDescription.getText().toString();
                String price = bookPrice.getText().toString();
                String subject = spinner.getSelectedItem().toString();

                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                if(user != null) {

                    String email = user.getEmail();

                    Books newBook = new Books(title, author, isbn, subject, description, price, email);

                    FirebaseFirestore db = FirebaseFirestore.getInstance();

                    db.collection("Books").add(newBook);

                    Toast t = Toast.makeText(CreateListingActivity.this, "Successfully Listed New Book", Toast.LENGTH_SHORT);
                    t.show();

                }
            }
        });
    }
}
