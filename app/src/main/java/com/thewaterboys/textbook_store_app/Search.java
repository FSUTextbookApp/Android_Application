package com.thewaterboys.textbook_store_app;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class Search extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search2);
        final Button searchBtn = findViewById(R.id.searchButton);


        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText Search = findViewById(R.id.search);

                Intent myIntent = new Intent(Search.this, SearchResults.class);
                Bundle myBundle = new Bundle();

                myBundle.putString("SearchOut", Search.getText().toString());

                String s = Search.toString();

                //Toast.makeText(Search.this,  Search.getText().toString(), Toast.LENGTH_LONG ).show();

                myIntent.putExtras(myBundle);

                startActivity(myIntent);
            }
        });

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

                        if(menuItem.getTitle().equals("Sign Out")) {
                            FirebaseAuth.getInstance().signOut();
                            Toast.makeText(Search.this, "Successfully Signed Out", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(Search.this, MainActivity.class);
                            startActivity(intent);
                        }

                        if(menuItem.getTitle().equals("About Us")) {
                            Intent intent = new Intent(Search.this, AboutUs.class);
                            startActivity(intent);
                        }

                        if(menuItem.getTitle().equals("Books by Subject")) {
                            Intent intent = new Intent(Search.this, ChooseSubject.class);
                            startActivity(intent);
                        }

                        if(menuItem.getTitle().equals("List a Book for Sale")) {
                            Intent intent = new Intent(Search.this, CreateListingActivity.class);
                            startActivity(intent);
                        }

                        if(menuItem.getTitle().equals("Search by Title")) {
                            //do nothing
                        }

                        if(menuItem.getTitle().equals("My Account")) {
                            Intent intent = new Intent(Search.this, MyAccount.class);
                            startActivity(intent);
                        }
                        return true;
                    }
                });

    }


}
