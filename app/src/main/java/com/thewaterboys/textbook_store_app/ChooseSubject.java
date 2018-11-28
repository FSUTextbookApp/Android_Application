package com.thewaterboys.textbook_store_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class ChooseSubject extends AppCompatActivity {

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
    }
}
