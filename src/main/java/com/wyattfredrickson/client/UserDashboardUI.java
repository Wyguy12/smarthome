package com.wyattfredrickson.client;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.util.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import com.wyattfredrickson.model.Device;
import com.wyattfredrickson.model.User;

/**
 * Smart Home Device class 
 * This class creates a user interface for the Smart Home Device
 * This will be capable of simulating connecting to living room lights, bedroom lights and the thermostat. 
 * It will be able to see the status of the living room lights, bedroom lights and thermostat. 
 * It will be able to provide feedback to the user confirming the various devices have been turned on or off.
 */
public class UserDashboardUI extends Application {
    // Declare variable of type User
    private User user;
    // Declare variable of type deviceClient
    private DeviceClient deviceClient;
    
    // Constructor with parameter of type User that creates an object instance. 
    public UserDashboardUI(User user) {
        this.user = user;
        this.deviceClient = new DeviceClient();
    }

    
    /**
     * Setup the stage
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            // Set the stage style to TRANSPARENT
            primaryStage.initStyle(StageStyle.TRANSPARENT);
            primaryStage.setTitle("User Dashboard");

            // Create new BorderPane instance
            BorderPane rootNode = new BorderPane();
            rootNode.setPadding(new Insets(20));
            rootNode.getStyleClass().add("background");
            
            // Create a new VBox instance 
            VBox topBox = new VBox();
            topBox.setAlignment(Pos.CENTER);
            topBox.setSpacing(10);

            // Create a new welcome label instance
            Label welcomeLabel = new Label("Hello " + user.getFirstName() + " " + user.getLastName());
            welcomeLabel.setPrefWidth(800);
            welcomeLabel.setAlignment(Pos.CENTER);
            welcomeLabel.getStyleClass().add("welcomeLabel");
            
            // Create new clock instance for displaying real time to the user
            Label dateTimeLabel = new Label();
            dateTimeLabel.getStyleClass().add("dateTimeLabel");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy hh:mma");
            Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
                dateTimeLabel.setText(LocalDateTime.now().format(formatter));
            }), new KeyFrame(Duration.seconds(1)));
            clock.setCycleCount(Animation.INDEFINITE);
            clock.play(); // Start clock
            // Add the welcomeLabel, dateTimeLabel to the topBox 
            topBox.getChildren().addAll(welcomeLabel, dateTimeLabel);
            rootNode.setTop(topBox);

            // Create a new VBox instance for all device controls
            VBox centerBox = new VBox();
            centerBox.setAlignment(Pos.CENTER);
            centerBox.setSpacing(20);
            centerBox.getChildren().add(createDeviceControl("Living Room Light", "1"));
            centerBox.getChildren().add(createDeviceControl("Bedroom Light", "2"));
            centerBox.getChildren().add(createDeviceControl("Thermostat", "3"));
            rootNode.setCenter(centerBox);

            // Logout button functionality
            Button logoutButton = new Button("Log out");
            logoutButton.getStyleClass().add("logoutButton");
            logoutButton.setOnAction(e -> {
                primaryStage.close(); // Close the stage
                try {
                    new UserLoginUI().start(new Stage());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            });

            // Create new HBox instance called bottomBox that holds the log out button
            HBox bottomBox = new HBox(logoutButton);
            bottomBox.setAlignment(Pos.BOTTOM_RIGHT);
            bottomBox.setPadding(new Insets(20));
            rootNode.setBottom(bottomBox);

            // Set the scene and show the stage
            Scene scene = new Scene(rootNode, 800, 800);
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.show();
            setUserAgentStylesheet(STYLESHEET_CASPIAN);
            scene.setFill(null);

            // Add stylesheet resource location
            scene.getStylesheets().add(getClass().getResource("/UserInterface.css").toExternalForm());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

        /**
         * Method for creating the device control interface.
         * @param deviceName The name of the device.
         * @param deviceId The ID of the device.
         * @return Returns a VBox instance containing all the controls interface for the device.
         */
        private VBox createDeviceControl(String deviceName, String deviceId) {
            // Create a new Device instance for holding the device ID and device name inside, as well as the device status true/false (ON or OFF)
            Device device = new Device(deviceId, deviceName, false);
            deviceClient.registerDevice(device);

            Label deviceNameLabel = new Label(device.getDeviceName());
            deviceNameLabel.getStyleClass().add("deviceNameLabel");
            Label deviceStatusLabel = new Label("OFF");
            deviceStatusLabel.getStyleClass().add("statusLabel");

            // On button creation and action
            Button onButton = new Button("Turn ON");
            onButton.getStyleClass().add("controlButton");
            onButton.setOnAction(e -> {
                deviceStatusLabel.setText("ON");
                deviceClient.controlDevice(device, true);
            });

            // Off button creation and action
            Button offButton = new Button("Turn OFF");
            offButton.getStyleClass().add("controlButton");
            offButton.setOnAction(e -> {
                deviceStatusLabel.setText("OFF");
                deviceClient.controlDevice(device, false);
            });

            // Create a new instance HBox for placement of both buttons
            HBox buttonBox = new HBox(10, onButton, offButton);
            buttonBox.setAlignment(Pos.CENTER);
            // Create a new instance VBox for placement of device name and device status, buttonBox
            VBox deviceBox = new VBox(10, deviceNameLabel, deviceStatusLabel, buttonBox);
            deviceBox.setAlignment(Pos.CENTER);
            deviceBox.setPadding(new Insets(10));
            return deviceBox;
        }

    public static void main(String[] args) {
        launch(args);
    }
}
