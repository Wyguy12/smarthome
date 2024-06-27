package com.wyattfredrickson.server;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wyattfredrickson.model.User;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * Public class that will handle reading and writing user data to and from the users.json file locally.
 * 
 */
public class UserFileManager {
    // File path location to users.json
    private static final String FILE_URL = System.getenv("USERS_FILE_PATH");
    private ObjectMapper objectMapper = new ObjectMapper();


    /**
     * Saving the list of users to the user.JSON file
     * @param users The ArrayList of users to be saved
     * @throws IOException Throws IOException if there is an I/O error that occurs
     */
    public void saveUsers(List<User> users) throws IOException {
        // If null throw error message
        if (FILE_URL == null) {
            throw new IOException("The environment variable USERS_FILE_PATH has not been set. Setup environment variable and echo USERS_FILE_PATH.");
        }
        // Create new file instance for the file and save 'users' inside of the file using objectMapper and "writeValue" method
        objectMapper.writeValue(new File(FILE_URL), users);
    }
     
    
    /**
     * Method will load the list of users from the users.json file
     * @return Returns the list of users from the ArrayList
     * @throws IOException Throws IOException if there is an I/O error that occurs
     */
    public List<User> loadUsers() throws IOException {
        // If null throw error message
        if (FILE_URL == null) {
            throw new IOException("The environment variable USERS_FILE_PATH has not been set. Setup environment variable and echo USERS_FILE_PATH.");
        }
        // Create new file instance for the file
        File file = new File(FILE_URL);
        // if file does not exist then return new empty arraylist
        if (!file.exists()) {
            return new ArrayList<>(); // Returns an empty arraylist if the file does not exist
        }
        return objectMapper.readValue(file, objectMapper.getTypeFactory().constructCollectionType(List.class, User.class));
    }
   

}