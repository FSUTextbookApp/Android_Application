package com.thewaterboys.textbook_store_app;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CreateAccount extends Activity {

    private static final String TAG = "CreateAccount";

    private FirebaseAuth mAuth;

    boolean formError = true;

    EditText FirstName;
    EditText LastName;
    EditText Email;
    EditText Password;
    EditText VPassword;
    EditText Phone;

    CheckBox Accept;

    Button Clear;
    Button Submit;

    boolean value = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        FirstName = findViewById(R.id.firstName);
        LastName = findViewById(R.id.lastName);
        Email = findViewById(R.id.email);
        Password = findViewById(R.id.password);
        VPassword = findViewById(R.id.verifyPassword);
        Phone = findViewById(R.id.phone);

        Accept = findViewById(R.id.accept_btn);

        Clear = findViewById(R.id.clear);
        Submit = findViewById(R.id.submit);

        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkData();
            }
        });
        Clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clear();
            }
        });


    }

    boolean isEmpty(EditText text){
        CharSequence str = text.getText().toString();

        if (TextUtils.isEmpty(str) == true)
            return true;
        else {
            value = false;
            return false;
        }
    }

    boolean nameLength(EditText text){

        if( text.length() <= 2)
            return true;
        else
        {
            value = false;
            return false;
        }

    }

    boolean isEmail(EditText text){
        CharSequence email = text.getText().toString();

        if (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches())
            return true;
        else
        {
            value = false;
            return false;
        }
    }

    boolean passwordLength(EditText text){

        if( text.length() >= 8)
            return true;
        else{
            value = false;
            return false;
        }

    }

    boolean passwordMatch() {

        if (Password == VPassword)
            return true;
        else
        {
            value = false;
            return false;
        }

    }

    boolean isPhone(EditText text){
        CharSequence phone = text.getText().toString();

        if (Patterns.PHONE.matcher(phone).matches() &&  phone.length() == 10)
            return true;
        else
        {
            value = false;
            return false;

        }
    }

    boolean isTrue(){
        return value;
    }

    void checkData(){
        if(nameLength(FirstName))
        {
            FirstName.setError("Missing First Name");
            formError = false;
        }

        if(nameLength(LastName))
        {
           LastName.setError("Missing Last Name");
            formError = false;
        }

        if(!isEmail(Email)){
            Email.setError("Invalid Email");
            formError = false;
        }

        if(passwordMatch()){
            Password.setError("Passwords don't match");
            formError = false;
        }

        if(!passwordLength(Password)){
            VPassword.setError("Passwords too short");
            formError=false;

        }

        if(!passwordLength(VPassword)){
            Toast toast = Toast.makeText(this, "Password too short", Toast.LENGTH_SHORT);
            toast.show();
            formError = false;
        }

        if(isPhone(Phone) == false){
           Phone.setError("Phone number invalid");
            formError = false;
        }

        if(!Accept.isChecked())
        {
            Toast t = Toast.makeText(this, "Please agree to terms", Toast.LENGTH_SHORT);
            t.show();
            formError = false;
        }

        if(!formError){
            formError = true;
        }
        else
        {
            Toast t = Toast.makeText(this, "Registration Successful", Toast.LENGTH_SHORT);
            t.show();
            Intent myIntent = new Intent (CreateAccount.this, AccountCreated.class);

            Bundle mybundle = new Bundle();

            mybundle.putString("FirstNameOut", FirstName.getText().toString());
            mybundle.putString("LastNameOut", LastName.getText().toString());
            mybundle.putString("EmailOut", Email.getText().toString());
            mybundle.putString("PasswordOut", Password.getText().toString());
            mybundle.putString("VPasswordOut", VPassword.getText().toString());
            mybundle.putString("PhoneOut", Phone.getText().toString());

            myIntent.putExtras(mybundle);

            FirebaseFirestore db = FirebaseFirestore.getInstance();
            /*Users newUser = new Users(mybundle.getString("FirstNameOut"), mybundle.getString("LastNameOut"), mybundle.getString("EmailOut"), mybundle.getString("PhoneOut"));
            db.collection("Users").add(newUser);*/

            //TESTING

            CollectionReference users = db.collection("Users");
            Map<String, Object> data1 = new HashMap<>();
            data1.put("FirstName", mybundle.getString("FirstNameOut"));
            data1.put("LastName", mybundle.getString("LastNameOut"));
            data1.put("Email", mybundle.getString("EmailOut"));
            data1.put("PhoneNumber", mybundle.getString("PhoneOut"));
            users.document(mybundle.getString("EmailOut")).set(data1);

            //END TESTING

            mAuth = FirebaseAuth.getInstance();

            mAuth.createUserWithEmailAndPassword(mybundle.getString("EmailOut"), mybundle.getString("PasswordOut"))
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d(TAG, "createUserWithEmail:success");
                                FirebaseUser user = mAuth.getCurrentUser();
                            }
                        }
                    });

            System.out.println(mybundle.getString("EmailOut"));
            System.out.println(mybundle.getString("PasswordOut"));


            startActivity(myIntent);

        }

    }

    void clear()
    {
        FirstName.setText("");
        FirstName.setError(null);
        LastName.setText("");
        LastName.setError(null);
        Email.setText("");
        Email.setError(null);
        Password.setText("");
        Password.setError(null);
        VPassword.setText("");
        VPassword.setError(null);
        Phone.setText("");
        Phone.setError(null);

        Toast.makeText(CreateAccount.this, "Fields Reset", Toast.LENGTH_LONG).show();

    }

}

