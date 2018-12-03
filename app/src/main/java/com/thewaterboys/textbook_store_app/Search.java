package com.thewaterboys.textbook_store_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Search extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        final Button searchBtn = findViewById(R.id.searchButton);


        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText Search = findViewById(R.id.search);

                Intent myIntent = new Intent(Search.this, SearchResults.class);
                Bundle myBundle = new Bundle();

                myBundle.putString("SearchOut", Search.getText().toString());

                String s = Search.toString();

                Toast.makeText(Search.this,  Search.getText().toString(), Toast.LENGTH_LONG ).show();

                myIntent.putExtras(myBundle);

                startActivity(myIntent);
            }
        });

    }


}
