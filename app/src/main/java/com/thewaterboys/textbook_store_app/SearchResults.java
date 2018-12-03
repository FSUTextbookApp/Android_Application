package com.thewaterboys.textbook_store_app;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class SearchResults extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference notebookRef = db.collection("Books");

    private NoteAdapter adapter;
    private Query query;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results2);


        final TextView SearchOut = findViewById(R.id.searchText);

        final Button newSearch = findViewById(R.id.newsearch);

        Intent myIntent = getIntent();

        Bundle myBundle = myIntent.getExtras();

        Toast.makeText(SearchResults.this, myBundle.getString("SearchOut"), Toast.LENGTH_LONG).show();

        String searchResult = myBundle.getString("SearchOut");

        SearchOut.setText(myBundle.getString("SearchOut"));

        newSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(SearchResults.this, Search.class);
                startActivity(myIntent);
            }
        });


        setUpRecyclerView(searchResult);

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
                            FirebaseAuth.getInstance().signOut();
                            Intent intent = new Intent(SearchResults.this, MainActivity.class);
                            startActivity(intent);
                        }

                        if(menuItem.getTitle().equals("About Us")) {
                            Intent intent = new Intent(SearchResults.this, AboutUs.class);
                            startActivity(intent);
                        }

                        if(menuItem.getTitle().equals("Books by Subject")) {
                            Intent intent = new Intent(SearchResults.this, ChooseSubject.class);
                            startActivity(intent);
                        }

                        if(menuItem.getTitle().equals("List a Book for Sale")) {
                            Intent intent = new Intent(SearchResults.this, CreateListingActivity.class);
                            startActivity(intent);
                        }

                        if(menuItem.getTitle().equals("Search by Title")) {
                            Intent intent = new Intent(SearchResults.this, Search.class);
                            startActivity(intent);
                        }

                        if(menuItem.getTitle().equals("My Account")) {
                            Intent intent = new Intent(SearchResults.this, MyAccount.class);
                            startActivity(intent);
                        }
                        return true;
                    }
                });

    }

    private void setUpRecyclerView(String search_result)
    {
        //checks if the search_result is equal to title of a listing in notebookRef
        //version 1

        if (search_result.equals(search_result)) {
            query = notebookRef.whereEqualTo("title", search_result);

            //query.orderBy("ISBN");
        }


        /*else if(search_result.equals(search_result)) {
            query = notebookRef.whereEqualTo("author", search_result);

            query.orderBy("title");
        }*/

        /*else if(search_result.equals(search_result)) {
            query = notebookRef.whereEqualTo("ISBN", search_result);

            query.orderBy("title");
        }*/

        else
            query = notebookRef.orderBy("title");





        FirestoreRecyclerOptions<Note> results = new FirestoreRecyclerOptions.Builder<Note>()
                .setQuery(query, Note.class)
                .build();

        adapter = new NoteAdapter(results);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);

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
