package com.wyattfredrickson.client;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import com.wyattfredrickson.model.UserAccountCreation;


/* 


I certify, that this computer program submitted by me is all of my own work, Wyatt Fredrickson.

Wyatt Fredrickson

06-27-2024

CSC 322 


Sources:

Book Resources:
Java Programming Book: Introduction to Java Programming and Data Structures, Comprehensive Version, 12th Edition
Java Network Programming, 4th Edition By Elliotte Rusty Harold

Website Links:
https://www.geeksforgeeks.org/handle-an-ioexception-in-java/
https://emaillistvalidation.com/blog/demystifying-email-validation-with-regex-the-ultimate-guide/
https://www.abstractapi.com/tools/email-regex-guide
https://www.squash.io/how-to-validate-email-address-with-regex/
https://www.baeldung.com/jackson-object-mapper-tutorial
https://jenkov.com/tutorials/javafx/togglebutton.html#:~:text=ToggleButton%20.-,Creating%20a%20ToggleButton,with%20the%20text%20Left%20on.
https://stackoverflow.com/questions/42383857/javafx-live-time-and-date
https://docs.oracle.com/javase/8/javafx/api/javafx/scene/control/Label.html
https://docs.oracle.com/javase/8/javafx/api/javafx/scene/control/Button.html
https://maven.apache.org/surefire/maven-surefire-plugin/usage.html
https://maven.apache.org/surefire/maven-surefire-plugin/
https://www.baeldung.com/java-serialization
https://docs.oracle.com/javase/8/docs/technotes/guides/serialization/index.html

*/


/**
 * Public class that is used for user account creation.
 * The user will enter in their details, first, last, email and password details.
 */
public class UserAccountCreationUI extends Application {
    // Set x and y cordinates to 0 by default
    private double xOffset = 0;
    private double yOffset = 0;
    

    /**
     * Setup the stage
     */
    @Override
    public void start(Stage primaryStage) {
        

        try {
            // Set the stage style to TRANSPARENT
            primaryStage.initStyle(StageStyle.TRANSPARENT);

            // Create VBox instance and place the main parent (rootNode) inside
            VBox rootNode = new VBox();
            rootNode.setAlignment(Pos.CENTER); 
            rootNode.setPadding(new Insets(50));
            rootNode.setSpacing(6);

            // Path location for the background image
            String imagePath = getClass().getResource("/images/image4.png").toExternalForm();
            // CSS inline styles
            rootNode.setStyle("-fx-background-image: url('" + imagePath + "'); -fx-background-size: cover; -fx-background-position: center center;"); 

            // Create a label instance for name of application
            Label appNameLabel = new Label("SMARTSANCTUARY");
            appNameLabel.setStyle("-fx-font-size: 28px; -fx-font-weight: bold; -fx-text-fill: white; -fx-padding: 20px 0; -fx-pref-width: 350px; -fx-alignment: center; -fx-background-color: linear-gradient(to bottom ,rgba(0, 0, 0, 0.8), rgba(0, 0, 0, 0.6)); -fx-background-radius: 15px; -fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.75), 10, 0.5, 0, 0);");
            // Create a label instance for the title
            Label titleLabel = new Label("Create Account");
            // Set the titlelabel centered
            titleLabel.setAlignment(Pos.CENTER);
            // CSS inline styles
            titleLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: white; -fx-padding-bottom: 20px;");

            // Create a label instance for the first name
            Label firstNameLabel = new Label("First Name");
            // CSS inline styles
            firstNameLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 16px; -fx-text-fill: white;");
            // Create a textfield instance for the first name entry
            TextField firstNameTextField = new TextField();
            firstNameTextField.setPrefSize(300, 40);
            // CSS inline styles 
            firstNameTextField.setStyle("-fx-background-radius: 15px; -fx-border-radius: 15px; -fx-padding: 10px; -fx-background-color: white;");          
            // Create new HBox instance and place firstNameLabel inside
            HBox firstNameHBox = new HBox(10, firstNameLabel);
            firstNameHBox.setAlignment(Pos.TOP_LEFT);
            
            // Create a label instance for the last name
            Label lastNameLabel = new Label("Last Name");
            // CSS inline styles
            lastNameLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 16px; -fx-text-fill: white;");
            // Create a textfield instance for the last name entry
            TextField lastNameTextField = new TextField();
            lastNameTextField.setPrefSize(300, 40);
            // CSS inline styles
            lastNameTextField.setStyle("-fx-background-radius: 15px; -fx-border-radius: 15px; -fx-padding: 10px; -fx-background-color: white;");          
            // Create new HBox instance and place lastNameLabel inside
            HBox lastNameHBox = new HBox(10, lastNameLabel);
            lastNameHBox.setAlignment(Pos.TOP_LEFT);

            // Create a label instance for the email
            Label emailLabel = new Label("Email");
            // CSS inline styles
            emailLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 16px; -fx-text-fill: white;");
            // Create a textfield instance for the email entry
            TextField emailTextField = new TextField();
            emailTextField.setPrefSize(300, 40);
            // CSS inline styles
            emailTextField.setStyle("-fx-background-radius: 15px; -fx-border-radius: 15px; -fx-padding: 10px; -fx-background-color: white;");          
            // Create new HBox instance and place emailLabel inside
            HBox emailHBox = new HBox(10, emailLabel);
            emailHBox.setAlignment(Pos.TOP_LEFT);
            
            // Create a label instance for the password
            Label passwordLabel = new Label("Password");
            // CSS inline styles
            passwordLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 16px; -fx-text-fill: white;");
            // Create a textfield instance for the password entry
            PasswordField passwordTextField = new PasswordField();
            passwordTextField.setPrefSize(300, 40);
            // CSS inline styles
            passwordTextField.setStyle("-fx-background-radius: 15px; -fx-border-radius: 15px; -fx-padding: 10px; -fx-background-color: white;");          
            // Create new HBox instance and place passwordLabel inside
            HBox passwordHBox = new HBox(10, passwordLabel);
            passwordHBox.setAlignment(Pos.TOP_LEFT);


            // Create a button instance for 'Create Account'
            Button createButton = new Button("Create Account");
            // CSS inline styles 
            createButton.getStyleClass().addAll("button", "create-account-button");
            // The create button action
            createButton.setOnAction(e -> {
                // Get the users individual text for each parameter
                String firstName = firstNameTextField.getText();
                String lastName = lastNameTextField.getText();
                String email = emailTextField.getText().toLowerCase();
                String password = passwordTextField.getText();
                // Creates an instance of the type UserAccountCreation
                UserAccountCreation userAccountCreation = new UserAccountCreation();
                // Place parameter values inside of variable successful and call createUser. 
                boolean successful = userAccountCreation.createUser(firstName, lastName, email, password);
                // If successful in creating user, prompt the user
                if (successful) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Account has been created successfully.");
                    alert.showAndWait(); 
                    new UserLoginUI().start(new Stage());
                    primaryStage.close();
                } else {
                    // Else not successful, prompt the user 
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Failed to create new user account.");
                    alert.showAndWait();
                }
            });


            // Create a bottom label and sign up button instances 
            Label alreadyHaveAccLabel = new Label("Have an account? ");
            // CSS styles
            alreadyHaveAccLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 14px; -fx-text-fill: white;");
            // Create new Button instance for "Log In" 
            Button signInButton = new Button("Log In");
            // CSS styles
            signInButton.getStyleClass().addAll("button", "secondary-button");
            // The sign in button action
            signInButton.setOnAction(e -> {
                try {
                    new UserLoginUI().start(new Stage());
                    primaryStage.close(); // Close the stage
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            });

            // Create a HBox instance for placing alreadyHaveAccLabel and signInButton inside
            HBox bottombuttonsBox = new HBox(alreadyHaveAccLabel, signInButton);
            bottombuttonsBox.setAlignment(Pos.CENTER);
            bottombuttonsBox.setSpacing(5);
            
            // Add all its children to the main node inside of the scene.
            rootNode.getChildren().addAll(appNameLabel, titleLabel, firstNameHBox, firstNameTextField, lastNameHBox, lastNameTextField, emailHBox, emailTextField, passwordHBox, passwordTextField, createButton, alreadyHaveAccLabel, signInButton, bottombuttonsBox);
            // Create a scene instance, place the main node inside of the scene.
            Scene scene = new Scene(rootNode, 500, 600);
            // Set the stage to fixed dimensions and disable resizing
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.setMaxWidth(500);
            primaryStage.setMaxHeight(600);
            // Scene background transparent
            scene.setFill(null);
            // Show the stage
            primaryStage.show();

            // Event handler for when the mouse is pressed
            // Get cordinates
            scene.setOnMousePressed(event -> {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            });
            // Event handler for when the mouse is dragged across the screen
            // Get cordinates
            scene.setOnMouseDragged(event -> {
                primaryStage.setX(event.getScreenX() - xOffset);
                primaryStage.setY(event.getScreenY() - yOffset);
            });
            // Add stylesheet resource location
            scene.getStylesheets().add(getClass().getResource("/UserInterface.css").toExternalForm());

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
