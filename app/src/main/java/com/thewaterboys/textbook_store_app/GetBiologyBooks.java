package com.thewaterboys.textbook_store_app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;

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

        final Spinner deptSpinner = findViewById(R.id.deptSpinner);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.departments,R.layout.support_simple_spinner_dropdown_item);
        deptSpinner.setAdapter(adapter1);

        Button btnSearch = findViewById(R.id.btnSearch);
        btnSearch.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                query = notebookRef.whereEqualTo("subject", "Math");
                  FirestoreRecyclerOptions<Note> options = new FirestoreRecyclerOptions.Builder<Note>()
                          .setQuery(query, Note.class)
                          .build();
                  NoteAdapter adapter3;
                  adapter3 = new NoteAdapter(options);
                  RecyclerView recyclerView = findViewById(R.id.recycler_view);
                  recyclerView.setAdapter(adapter3);

              }
        });


        setUpRecyclerView();
    }

    private void setUpRecyclerView() {
        //Query query = notebookRef.orderBy("priority", Query.Direction.DESCENDING);
        query = notebookRef.whereEqualTo("subject", "Math");

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