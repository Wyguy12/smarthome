package com.wyattfredrickson.client;

import com.wyattfredrickson.model.Device;

import java.io.*;
import java.net.Socket;


/**
 * This is the device client class for Smart Home Control.
 * This class will connect to the device client server class that will be used to get the device status. 
 * It connects to another server to get the devices status (DeviceClient) in this case.
 * User will be able to see that the device is turning ON/OFF via a console message.
 */
public class DeviceClient {
    // The socket used to communicate with the server
    private Socket socket;
    // The input stream used to receive data from the serve-side
    private ObjectInputStream input;
    // The output stream used to send data to the client-side
    private ObjectOutputStream output;


    /**
     * Constructor method for the DeviceClient class. (Creates an object of itself).
     * Creating a connection to the server on a "localhost" at port "123".
     */
    public DeviceClient() {
        // Initialize the socket and streams
        try {
            socket = new Socket("localhost", 123); // Connect to the server on localhost and port 123
            // Create a new output stream for sending data to the server-side
            output = new ObjectOutputStream(socket.getOutputStream());
            // Create a new input stream for receiving data from the server-side
            input = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Method for controlling the device by setting the status of it to either ON or OFF.
     * @param device The device to be controlled.
     * @param state The state to set the device whether its ON or OFF.
     */
    public void controlDevice(Device device, boolean state) {
        // Send a control command signal to the server to set the device state (ON/OFF)
        try {
            // Write the action type to the server-side
            output.writeUTF("CONTROL");
            // Write the device ID to the server-side
            output.writeUTF(device.getDeviceId());
            // Write the state to the server (true/false ON/OFF)
            output.writeBoolean(state);
            // Flushes the output stream to ensure the data is being sent
            output.flush();
            // Reading the response from the server-side
            String response = input.readUTF();
            System.out.println(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    

    /**
     * Method that gets the status of the device
     * @param deviceId Gets the deviceID 
     * @return Returns the device with the specific ID.
     */
    public Device getDeviceStatus(String deviceId) {
        // Sending a status report to the server-side and receiving the device status back
        try {
            // Write the action type to the server-side
            output.writeUTF("STATUS");
            // Write the device ID to the server-side
            output.writeUTF(deviceId);
            // Flushes the output stream to ensure the data is being sent
            output.flush();
            // Reading the device object from the server-side response
            Device device = (Device) input.readObject();
            return device;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    
    /**
     * Method that is for registering a new device.
     * @param device The device that is to be registerd.
     */
    public void registerDevice(Device device) {
        // Send a "register" command to the server-side in order to register a new device
        try {
            // Write the action type to the server-side
            output.writeUTF("REGISTER");
            // Write the device object to the server-side
            output.writeObject(device);
            // Flushes the output stream to ensure the data is being sent
            output.flush();
            // Reading the response from the server-side
            String response = input.readUTF();
            System.out.println(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
