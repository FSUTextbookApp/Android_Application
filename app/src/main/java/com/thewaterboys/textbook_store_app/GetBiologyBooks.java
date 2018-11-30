package com.thewaterboys.textbook_store_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;

//MARCUS BARNES
//PLEASE GET WITH MARCUS if you need to change this page

public class GetBiologyBooks extends AppCompatActivity {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference notebookRef = db.collection("Books");
    //public Spinner spinner = findViewById(R.id.deptSpinner);
   //public String spinnerText = "All";
    //public Spinner deptSpinner = findViewById(R.id.deptSpinner);

   //public Button searchButton = findViewById(R.id.btnSearch);

    private NoteAdapter adapter;
    private Query query;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_biology_books);

        final TextView departmento = findViewById(R.id.subjectText);

//        final Spinner deptSpinner = findViewById(R.id.deptSpinner);
//        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.departments,R.layout.support_simple_spinner_dropdown_item);
//        deptSpinner.setAdapter(adapter1);

        Intent myintent = getIntent();
        Bundle mybundle = myintent.getExtras();
        String subject = mybundle.getString("spinnerout");

        departmento.setText(mybundle.getString("spinnerout"));


        setUpRecyclerView(subject);
    }

    private void setUpRecyclerView(String subject) {
        //Query query = notebookRef.orderBy("priority", Query.Direction.DESCENDING);
        if (subject.equals("All"))
            query = notebookRef.orderBy("title");
        else
            query = notebookRef.whereEqualTo("subject", subject);

        FirestoreRecyclerOptions<Note> options = new FirestoreRecyclerOptions.Builder<Note>()
                .setQuery(query, Note.class)
                .build();

        adapter = new NoteAdapter(options);

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