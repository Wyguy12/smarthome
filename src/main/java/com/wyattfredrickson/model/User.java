package com.wyattfredrickson.model;


/**
 * 
 * 
 * Public class that will represent a single user object in the smarthome.
 * 
 */
public class User {

    // Variables for the attributes of a User object
    private String firstName;
    private String lastName;
    private String email;
    private String password;


    /**
     * No-args constructor
     */
    public User() {

    }


    /**
     * Constructor that is an instance of a 'user' object with all of its attributes
     * @param firstName attribute
     * @param lastName attribute
     * @param email attribute
     * @param password attribute
     */
    public User(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }


    /**
     * 
     * @return first name
     */
    public String getFirstName() {
        return firstName;
    }


    /**
     * 
     * @param firstName first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


    /**
     * 
     * @return last name
     */
    public String getLastName(){
        return lastName;
    }


    /**
     * 
     * @param lastName last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    /**
     * 
     * @return email
     */
    public String getEmail() {
        return email;
    }


    /**
     * 
     * @param email email
     */
    public void setEmail(String email) {
        this.email = email;
    }


    /**
     * 
     * @return password
     */
    public String getPassword() {
        return password; 
    }


    /**
     * 
     * @param password password
     */
    public void setPasssword(String password) {
        this.password = password;
    }

}