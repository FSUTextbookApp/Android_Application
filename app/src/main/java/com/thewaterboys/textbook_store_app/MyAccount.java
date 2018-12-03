package com.thewaterboys.textbook_store_app;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.auth.User;

public class MyAccount extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference BookRef = db.collection("Books");
    private CollectionReference UserRef = db.collection("Users");
    final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    private DocumentReference userDocRef = db.collection("Users").document("jcarter@email.com");
    private NoteAdapter adapter;
    private Query query;

    final static String TAG = "MyAccount";
    String name;
    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);



        if (user != null) {
            // Name, email address, and profile photo Url
            name = user.getDisplayName();
            email = user.getEmail();

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

            final EditText FirstName = (EditText) findViewById(R.id.firstName2);
            final EditText LastName = (EditText) findViewById(R.id.lastName2);
            EditText PhoneNumber = (EditText) findViewById(R.id.phoneNumber);

            ///this grabs a particular document!!! needs work on, i had to leave
            userDocRef.get()
                    .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            if (documentSnapshot.exists()) {

                                LastName.setText(documentSnapshot.getString("LastName"));


                                //Map<String, Object> note = documentSnapshot.getData();


                            } else {
                                Toast.makeText(MyAccount.this, "Document does not exist", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

            //FOLLOWING CODE IS TO TEST PULLING USER DATA FROM FIRESTORE DATABASE

            CollectionReference usersRef = db.collection("Users");

            Query query = usersRef.whereEqualTo("Email", email);

            System.out.println("TEST QUERY PRINT: " + query);

            DocumentReference docRef = db.collection("Users").document(email);
            docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {
                            Log.d(TAG, "DocumentSnapshot data: " + document.getData());
                        } else {
                            Log.d(TAG, "No such document");
                        }
                    } else {
                        Log.d(TAG, "get failed with ", task.getException());
                    }
                }
            });



            //END TEST

            Intent myIntent = getIntent();

            Bundle mybundle = myIntent.getExtras();

            //FOLLOWING CODE IS NOT NEEDED, CAUSES CRASH WHEN NAVIGATING TO MY ACCOUNT PAGE

            /*String bookPostingEmail = mybundle.getString("sellerEmail");
            String bookTitle = mybundle.getString("bookTitle");

            System.out.println(user.getEmail());
            System.out.println(bookPostingEmail);

            if(user.getEmail().equals(bookPostingEmail)) {
                Toast t = Toast.makeText(this, bookTitle, Toast.LENGTH_SHORT);
                t.show();
            }*/

            /*System.out.println("TEST TEST TEST");
            System.out.println("EMAIL IS: ");
            System.out.println(bookPostingEmail);*/
        }

        Button DeleteAccountButton = (Button) findViewById(R.id.delAccBtn);
        Button createListingButton = (Button) findViewById(R.id.createListing);

        createListingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent (MyAccount.this, CreateListingActivity.class);
                startActivity(myIntent);
            }
        });

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

                Intent myIntent = new Intent (MyAccount.this, MainActivity.class);
                startActivity(myIntent);
            }
        });

        setUpRecyclerView();



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
                            Intent intent = new Intent(MyAccount.this, MainActivity.class);
                            startActivity(intent);
                        }

                        if(menuItem.getTitle().equals("About Us")) {
                            Intent intent = new Intent(MyAccount.this, AboutUs.class);
                            startActivity(intent);
                        }

                        if(menuItem.getTitle().equals("Books by Subject")) {
                            Intent intent = new Intent(MyAccount.this, ChooseSubject.class);
                            startActivity(intent);
                        }

                        if(menuItem.getTitle().equals("List a Book for Sale")) {
                            Intent intent = new Intent(MyAccount.this, CreateListingActivity.class);
                            startActivity(intent);
                        }

                        if(menuItem.getTitle().equals("My Account")) {
                            //do nothing
                        }
                        return true;
                    }
                });

    }

    private void setUpRecyclerView() {
        //Query query = notebookRef.orderBy("priority", Query.Direction.DESCENDING);
        query = BookRef.whereEqualTo("sellerEmail", email);

        FirestoreRecyclerOptions<Note> options = new FirestoreRecyclerOptions.Builder<Note>()
                .setQuery(query, Note.class)
                .build();

        adapter = new NoteAdapter(options);

        RecyclerView recyclerView = findViewById(R.id.recycler_view_user_books);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                adapter.deleteItem(viewHolder.getAdapterPosition());
            }
        }).attachToRecyclerView(recyclerView);
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