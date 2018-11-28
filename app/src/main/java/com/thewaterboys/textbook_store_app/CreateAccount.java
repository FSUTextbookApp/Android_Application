package com.thewaterboys.textbook_store_app;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class CreateAccount extends Activity {

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

        FirstName = findViewById(R.id.email);
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

        if(isTrue()){
            Toast t = Toast.makeText(this, "Registration Successful", Toast.LENGTH_SHORT);
            t.show();
    }


    //permanently set to true once any error trips, needs to be reset to false once error is corrected.
        /*if(!formError)
        {
            Toast.makeText(this, "Error in form", Toast.LENGTH_LONG).show();
        }*/


        else
        {
            Intent myIntent = new Intent (CreateAccount.this, AccountCreated.class);

            Bundle mybundle = new Bundle();

            mybundle.putString("FirstNameOut", FirstName.getText().toString());
            mybundle.putString("LastNameOut", LastName.getText().toString());
            mybundle.putString("EmailOut", Email.getText().toString());
            mybundle.putString("PasswordOut", Password.getText().toString());
            mybundle.putString("VPasswordOut", VPassword.getText().toString());
            mybundle.putString("PhoneOut", Phone.getText().toString());

            myIntent.putExtras(mybundle);

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

