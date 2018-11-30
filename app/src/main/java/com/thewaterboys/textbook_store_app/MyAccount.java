package com.thewaterboys.textbook_store_app;

import android.content.Intent;
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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MyAccount extends AppCompatActivity {

    final static String TAG = "MyAccount";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user != null) {
            // Name, email address, and profile photo Url
            String name = user.getDisplayName();
            String email = user.getEmail();

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

    }
}
