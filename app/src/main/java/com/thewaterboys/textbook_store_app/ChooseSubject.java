package com.thewaterboys.textbook_store_app;



import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class ChooseSubject extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_subject);

        final Spinner spinner = findViewById(R.id.subjectSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.departments,R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


        Button searchButton =  findViewById(R.id.searchButton);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(ChooseSubject.this, GetBiologyBooks.class));

                Intent myintent = new Intent(ChooseSubject.this, GetBiologyBooks.class);
                Bundle mybundle = new Bundle();
                String spinnerText = spinner.getSelectedItem().toString();
                mybundle.putString("spinnerout", spinnerText);
                myintent.putExtras(mybundle);
                startActivity(myintent);
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
                            Toast.makeText(ChooseSubject.this, "Successfully Signed Out", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(ChooseSubject.this, MainActivity.class);
                            startActivity(intent);
                        }

                        if(menuItem.getTitle().equals("About Us")) {
                            Intent intent = new Intent(ChooseSubject.this, AboutUs.class);
                            startActivity(intent);
                        }

                        if(menuItem.getTitle().equals("Books by Subject")) {
                            Intent intent = new Intent(ChooseSubject.this, ChooseSubject.class);
                            startActivity(intent);
                        }

                        if(menuItem.getTitle().equals("List a Book for Sale")) {
                            Intent intent = new Intent(ChooseSubject.this, CreateListingActivity.class);
                            startActivity(intent);
                        }

                        if(menuItem.getTitle().equals("Search by Title")) {
                            Intent intent = new Intent(ChooseSubject.this, Search.class);
                            startActivity(intent);
                        }

                        if(menuItem.getTitle().equals("My Account")) {
                            Intent intent = new Intent(ChooseSubject.this, MyAccount.class);
                            startActivity(intent);
                        }
                        return true;
                    }
                });
    }
}
