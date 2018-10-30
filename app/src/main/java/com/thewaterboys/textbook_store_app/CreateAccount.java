package com.thewaterboys.textbook_store_app;

import android.os.Bundle;
import android.app.Activity;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CreateAccount extends Activity {

    EditText FirstName;
    EditText LastName;
    EditText Email;
    EditText Password;
    EditText VPassword;
    EditText Phone;

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
            Toast toast = Toast.makeText(this, "First Name too short", Toast.LENGTH_SHORT);
            toast.show();
        }

        if(nameLength(LastName))
        {
            Toast toast = Toast.makeText(this, "Last Name too short", Toast.LENGTH_SHORT );
            toast.show();
        }

        if(isEmail(Email) == false){
            Toast t = Toast.makeText(this, "Invalid email", Toast.LENGTH_SHORT);
            t.show();
        }

        if(passwordMatch()){
            Toast toast = Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT);
            toast.show();
        }

        if(passwordLength(Password) == false){
            Toast toast = Toast.makeText(this, "Password too short", Toast.LENGTH_SHORT);
            toast.show();
        }

        if(passwordLength(VPassword) == false){
            Toast toast = Toast.makeText(this, "Password too short", Toast.LENGTH_SHORT);
            toast.show();
        }

        if(isPhone(Phone) == false){
            Toast t = Toast.makeText(this, "Phone number invalid", Toast.LENGTH_SHORT);
            t.show();
        }

        if(isTrue()){
            Toast t = Toast.makeText(this, "Registration Successful", Toast.LENGTH_SHORT);
            t.show();
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

        Toast.makeText(CreateAccount.this, "Feilds Reset", Toast.LENGTH_LONG).show();

    }

}

