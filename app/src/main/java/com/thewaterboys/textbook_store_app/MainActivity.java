package com.thewaterboys.textbook_store_app;

import android.content.Intent;
import android.print.PrintDocumentAdapter;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    boolean formerror = false;

    private final static String TAG = "MainActivity";

    private Button btnViewDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final FirebaseAuth mAuth;
        mAuth = FirebaseAuth.getInstance();

        Button logInButton = (Button) findViewById(R.id.logIn);

        final EditText EmailIn = findViewById(R.id.emailSignIn);
        final EditText PasswordIn = findViewById(R.id.passwordSignIn);

        logInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailString = EmailIn.getText().toString();
                String passwordString = PasswordIn.getText().toString();

                //if text on email view and password are not null
                if (emailString.matches("")) {
                    formerror = true;
                    EmailIn.setError("Missing Email");
                }
                if (passwordString.matches("")) {
                    formerror = true;
                    PasswordIn.setError("Missing Password");
                }
                else {
                    String email = EmailIn.getText().toString();
                    String password = PasswordIn.getText().toString();

                    System.out.println(email);
                    System.out.println(password);

                    FirebaseFirestore db = FirebaseFirestore.getInstance();

                    final Books newBook = new Books("Machine Learning- Math", "Sharanya", "123456789", "Sociology", "this is a short description of the book this is a short description of the book this is a short description", "98", "email@fsu.edu");
                    db.collection("Books").add(newBook);

                    mAuth.signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        Log.d(TAG, "signInWithEmail:success");
                                        FirebaseUser user = mAuth.getCurrentUser();
                                        Toast.makeText(MainActivity.this, "Authentication Success.", Toast.LENGTH_SHORT).show();
                                        Toast.makeText(MainActivity.this, "Welcome back " + user.getEmail() + "!", Toast.LENGTH_SHORT).show();

                                        updateUI(user, newBook);
                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Log.w(TAG, "signInWithEmail:failure", task.getException());
                                        Toast.makeText(MainActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });

        Button SignUpBtn = (Button) findViewById(R.id.SignUp);

        SignUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CreateAccount.class));
            }
        });

        //This is a flexable button that should be deleted before presenting*****************************8888***
        Button flexBtn =  findViewById(R.id.flexButton);
        flexBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Search.class));
            }
        });
    }

    public void updateUI(FirebaseUser usr, final Books b) {
        Intent intent = new Intent(MainActivity.this, MyAccount.class);

        Bundle mybundle = new Bundle();

        //mybundle.putString("sellerEmail", b.sellersEmail);
        mybundle.putString("bookTitle", b.title);
        mybundle.putString("bookAuthor", b.author);
        mybundle.putString("bookISBN", b.ISBN);
        mybundle.putString("bookPrice", b.price);
        mybundle.putString("bookSubject", b.subject);

        intent.putExtras(mybundle);

        startActivity(intent);
    }
}