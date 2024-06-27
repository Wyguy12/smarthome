package com.wyattfredrickson.model;

import com.wyattfredrickson.server.UserFileManager;
import java.io.IOException;
import java.util.List;


/**
 * Public class for the user account creation
 */
public class UserAccountCreation {
    // Declare a variable of the type "UserFileManager"
    private UserFileManager userFileManager;


    /*
     * No-args constructor that creates a userFileManager object for loading/saving user accounts
     */
    public UserAccountCreation() {
        this.userFileManager = new UserFileManager();
    }


    /**
     * 
     * Method that creates a new user with all of its details
     *
     * @param firstName The user's first name
     * @param lastName The user's last name
     * @param email The user's email address
     * @param password The user's password
     * @return Returns true if the user account was created successfully, otherwise returns false
     */
    public boolean createUser(String firstName, String lastName, String email, String password) {
        email = email.toLowerCase(); // Call toLowerCase method 
        
        // Checking if any of the parameters are null or empty, if they are then it will return false indicating the validation process has failed.
        if (firstName == null || firstName.isEmpty() || lastName == null || lastName.isEmpty() || email == null || email.isEmpty() || !isValidEmail(email) || password == null || password.isEmpty()) {
            return false;
        }
        // creates a new user instance using all the parameters of a new user
        User newUserCreation = new User(firstName, lastName, email, password);

        try {
            // Load in the new users via variable UserFileManager utilizing the ArrayList called 'users'
            // Calling the method 'loadUsers' 
            List<User> users = userFileManager.loadUsers();

            // Logic checking if the email does already exist and that its case insensitive.
            for (User user : users) {
                if (user.getEmail().equalsIgnoreCase(email)) {
                    return false;
                }
            }

            users.add(newUserCreation); // Add the new user account creations to the ArrayList 'users'
            
            // Store the new user account creations inside of the ArrayList 'users' via the UserFileManager
            // Calling the method 'saveUsers'
            userFileManager.saveUsers(users);
        }   catch (IOException e) {
            e.printStackTrace();
            return false; // Returns false indicated it failed to attempt to save the new user accounts
        }
        return true; // Returns true indicating the new user account creations are successful

    }


    /**
     * Method that is used to validate the email
     * @param email the User's email
     * @return Returns true if the email is valid. otherwise returns false
     */
    private boolean isValidEmail(String email) {
        // Using encoded text strings desgined to match the patterns in other strings like an email address used in this example.
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-z0-9_+&*-]+)*@(?:[a-zA-z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return email.matches(emailRegex);
    }

}