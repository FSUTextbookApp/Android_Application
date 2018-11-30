package com.thewaterboys.textbook_store_app;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class MyAccount extends AppCompatActivity {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference BookRef = db.collection("Books");
    private NoteAdapter adapter;
    private Query query;

    final static String TAG = "MyAccount";
    String name;
    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user != null) {
            // Name, email address, and profile photo Url
            name = user.getDisplayName();
            email = user.getEmail();

            // Check if user's email is verified
            boolean emailVerified = user.isEmailVerified();

            if(emailVerified) {
                Toast t = Toast.makeText(this, "Email Verified!", Toast.LENGTH_SHORT);
                t.show();
            }
            else {
                Toast t = Toast.makeText(this, "Must verify your email!", Toast.LENGTH_SHORT);
                t.show();

                user.sendEmailVerification()
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Log.d(TAG, "Email sent.");
                                }
                            }
                        });
            }


            EditText Email = (EditText) findViewById(R.id.email2);
            Email.setText(email);

            Intent myIntent = getIntent();

            Bundle mybundle = myIntent.getExtras();

            String bookPostingEmail = mybundle.getString("sellerEmail");
            String bookTitle = mybundle.getString("bookTitle");

            System.out.println(user.getEmail());
            System.out.println(bookPostingEmail);

            if(user.getEmail().equals(bookPostingEmail)) {
                Toast t = Toast.makeText(this, bookTitle, Toast.LENGTH_SHORT);
                t.show();
            }

            /*System.out.println("TEST TEST TEST");
            System.out.println("EMAIL IS: ");
            System.out.println(bookPostingEmail);*/
        }

        Button DeleteAccountButton = (Button) findViewById(R.id.delAccBtn);
        Button createListingButton = (Button) findViewById(R.id.createListing);

        createListingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent (MyAccount.this, CreateListingActivity.class);
                startActivity(myIntent);
            }
        });

        DeleteAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                user.delete()
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Log.d(TAG, "User account deleted.");
                                }
                            }
                        });
            }
        });

        setUpRecyclerView();

    }

    private void setUpRecyclerView() {
        //Query query = notebookRef.orderBy("priority", Query.Direction.DESCENDING);
        query = BookRef.whereEqualTo("sellersEmail", email);

        FirestoreRecyclerOptions<Note> options = new FirestoreRecyclerOptions.Builder<Note>()
                .setQuery(query, Note.class)
                .build();

        adapter = new NoteAdapter(options);

        RecyclerView recyclerView = findViewById(R.id.recycler_view_user_books);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}
