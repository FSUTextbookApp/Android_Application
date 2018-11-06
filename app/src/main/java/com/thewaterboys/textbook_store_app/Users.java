package com.thewaterboys.textbook_store_app;

public class Users {

    public String FirstName;
    public String LastName;
    public String Email;
    public String PhoneNumber;

    public Users() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public Users(String FirstName, String LastName, String Email, String PhoneNumber) {
        this.FirstName = FirstName;
        this.LastName = LastName;
        this.Email = Email;
        this.PhoneNumber = PhoneNumber;
    }

}
