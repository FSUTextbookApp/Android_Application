package com.thewaterboys.textbook_store_app;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class AccountCreated extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;


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

        mDrawerLayout = findViewById(R.id.drawer_layout);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        // set item as selected to persist highlight
                        menuItem.setChecked(true);
                        // close drawer when item is tapped
                        mDrawerLayout.closeDrawers();

                        // Add code here to update the UI based on the item selected
                        // For example, swap UI fragments here

                        if(menuItem.getTitle().equals("Home Page")) {
                            Intent intent = new Intent(AccountCreated.this, MainActivity.class);
                            startActivity(intent);
                        }

                        if(menuItem.getTitle().equals("About Us")) {
                            Intent intent = new Intent(AccountCreated.this, AboutUs.class);
                            startActivity(intent);
                        }

                        if(menuItem.getTitle().equals("Books by Subject")) {
                            Intent intent = new Intent(AccountCreated.this, ChooseSubject.class);
                            startActivity(intent);
                        }

                        if(menuItem.getTitle().equals("List a Book for Sale")) {
                            Intent intent = new Intent(AccountCreated.this, CreateListingActivity.class);
                            startActivity(intent);
                        }

                        if(menuItem.getTitle().equals("My Account")) {
                            Intent intent = new Intent(AccountCreated.this, MyAccount.class);
                            startActivity(intent);
                        }

                        return true;
                    }
                });
    }
}
