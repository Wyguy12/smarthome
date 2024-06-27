package com.wyattfredrickson.client;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import com.wyattfredrickson.server.UserFileManager;
import com.wyattfredrickson.model.User;
import java.io.IOException;
import java.util.List;


/**
 * This is the user login dashboard class for Smart Home Control.
 * It will have the ability to log in a user via email and password. 
 * It will also have another button "Sign Up". The user can click this button 
 * and it will change to the "UserAccountCreationApp" for creation of a user account.
 * This class will create a login user interface for user authentication.
 */
public class UserLoginUI extends Application {
        // Set x and y coordinates to 0 by default
        private double xOffset = 0;
        private double yOffset = 0;
        
        
    /**
     * Setup the stage
     */
    @Override
    public void start(Stage primaryStage) {

  
        try {

            // Set the stage style to UNDECORATED
            primaryStage.initStyle(StageStyle.UNDECORATED);
           
            // Create a label instance for name of application
            Label applicationNameLabel = new Label("SMARTSANCTUARY");
            // CSS Styles
            applicationNameLabel.getStyleClass().add("applicationNameLabel");
            // Create the title label instance
            Label titleLoginLabel = new Label("Please login to your account");
            // CSS styles
            titleLoginLabel.getStyleClass().add("titleLabel"); 

            // Create the email label and text field instances
            Label emailLabel = new Label("Email");
            TextField emailTextField = new TextField();
            emailTextField.setPrefSize(300, 40); 
            emailLabel.setAlignment(Pos.TOP_LEFT);
            // CSS styles
            emailLabel.getStyleClass().add("emailLabel"); 
            emailTextField.getStyleClass().add("emailTextField");
            // Create a VBox instance for the email label and email textfield
            // Adjusting the vertical dimension
            VBox emailVBox = new VBox(5, emailLabel, emailTextField);
            emailVBox.setAlignment(Pos.CENTER_LEFT);


            // Create the password label and text field instances
            Label passwordLabel = new Label("Password");
            PasswordField passwordTextField = new PasswordField();
            passwordTextField.setPrefSize(300, 40);
            passwordLabel.setAlignment(Pos.TOP_LEFT);
            // CSS styles
            passwordLabel.getStyleClass().add("passwordLabel");
            passwordTextField.getStyleClass().add("passwordTextField");
            // Create a VBox instance for the password label and password textfield
            // Adjusting the vertical dimension
            VBox passwordVBox = new VBox(5, passwordLabel, passwordTextField);
            passwordVBox.setAlignment(Pos.CENTER_LEFT);


            // Create a new login button instance
            Button loginButton = new Button("LOGIN");
            // CSS styles
            loginButton.getStyleClass().addAll("button", "login-button");
            // The login button action
            loginButton.setOnAction(e -> {
                // Get the text from the email and password text fields. 
                String email = emailTextField.getText();
                String password = passwordTextField.getText();
                User authenticatedUser = authenticateUser(email, password);
                // Authenticating the user and then moves to the dashboard screen once it is accepted
                if (authenticatedUser != null) {
                    try {
                        new UserDashboardUI(authenticatedUser).start(new Stage());
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                    primaryStage.close();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid email or password.");
                    alert.showAndWait();
                }
            });

            // Create new button instance for "sign up" 
            Button signupButton = new Button("Sign up");
            // CSS styles
            signupButton.getStyleClass().addAll("button", "secondary-button");
            // The sign in button action
            signupButton.setOnAction(e -> {
                try {
                    new UserAccountCreationUI().start(new Stage());
                    primaryStage.close(); 
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            });

            // Create a VBox to hold all the children inside
            VBox vbox = new VBox(10);
            vbox.setAlignment(Pos.CENTER);
            vbox.setPadding(new Insets(20, 50, 20, 50)); 
            vbox.getChildren().addAll(applicationNameLabel, titleLoginLabel, emailVBox, passwordVBox, loginButton, signupButton);

            // Create a bottom label and sign up button instances 
            Label bottomLabel = new Label("Don't have an account? Sign up below");
            // CSS styles
            bottomLabel.getStyleClass().add("bottomLabel");
            signupButton.getStyleClass().add("signupButton");
            

            // Create a VBox for the bottom elements
            VBox bottomBox = new VBox(5);
            // CSS styles
            bottomBox.getStyleClass().add("bottomBox");
            bottomBox.setAlignment(Pos.CENTER);
            bottomBox.getChildren().addAll(bottomLabel, signupButton); // Add the nodes (children) to the bottom VBox. 
           
            // Create a BorderPane instance and add the vbox and bottomBox
            BorderPane borderPane = new BorderPane();
            borderPane.setCenter(vbox);
            borderPane.setBottom(bottomBox);
            BorderPane.setAlignment(bottomBox, Pos.CENTER);
            BorderPane.setMargin(bottomBox, new Insets(0, 0, 20, 0)); // Adjusting the margin
            borderPane.setPrefSize(500, 500);
    
            // Set the scene and show the stage
            // Set the stage to fixed dimensions and disable resizing
            Scene scene = new Scene(borderPane, 500, 600);
            primaryStage.setScene(scene);
            primaryStage.setWidth(500);
            primaryStage.setHeight(600);
            primaryStage.setResizable(false);
            primaryStage.show();

            // Apply the background CSS class to the BorderPane
            borderPane.getStyleClass().add("background");
            // Add stylesheet resource location
            scene.getStylesheets().add(getClass().getResource("/UserInterface.css").toExternalForm());   
            
            // Event handler for when the mouse is pressed
            // Get coordinates
            scene.setOnMousePressed(event -> {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            });
            // Event handler for when the mouse is dragged across the screen
            // Get coordinates
            scene.setOnMouseDragged(event -> {
                primaryStage.setX(event.getScreenX() - xOffset);
                primaryStage.setY(event.getScreenY() - yOffset);
            });

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    /**
     * Method handles authenticating the user via checking the email and password.
     * It will return a single User object.
     * @param email The user's email address.
     * @param password The user's password.
     * @return Returns true if the User object has been authenticated, otherwise returns false.
     */
    private User authenticateUser(String email, String password) {
        try {
            UserFileManager userFileManager = new UserFileManager();
            List<User> users = userFileManager.loadUsers();
            // Returns the first and last name if it find the first name
            return users.stream().filter(user -> user.getEmail().equals(email) && user.getPassword().equals(password)).findFirst().orElse(null);
        } catch (IOException e) {
            e.printStackTrace();
            return null; // Else return null
        }
    }

    
    public static void main(String[] args) {
            launch(args);
    }
}