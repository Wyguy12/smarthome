package com.wyattfredrickson.server;

import com.wyattfredrickson.model.Device;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;


/**
 * This is the server class used for Smart Home Control.
 * This class will simulate the device server that sends the device status.
 */
public class DeviceServer {
    // Create a new HashMap that stores the devices by key value pairs using a String primitive type
    private final Map<String, Device> devices = new HashMap<>();
    // Initialize ServerSocket
    private ServerSocket serverSocket;


    /**
     * Method starts the device server.
     */
    public void startServer() {
        // Initialize the ServerSocket and start listening on port 123
        try (ServerSocket serverSock = new ServerSocket(123)) {
            // Assign the ServerSocket
            this.serverSocket = serverSock;
            System.out.println("Device server starting on port 123..");
            // Accepts client connections continously
            while (true) {
                // Accepts a new clientSocket 
                Socket clientSocket = serverSock.accept();
                // Starts-up a new thread to handle the client connection
                new ClientHandler(clientSocket).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Method stops the device server.
     */
    public void stopServer() {
        // Close the ServerSocket if it is not already closed
        try {
            // Checking if the server socket is not null and is open as well.
            if (serverSocket != null && !serverSocket.isClosed()) {
                serverSocket.close();
                System.out.println("Stopping device server..");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Method that registers the device to the server.
     * @param device whichever device to be registered.
     */
    public synchronized void registeringDevice(Device device) {
        // Adding the device to the devices map using its ID as the key value pair
        devices.put(device.getDeviceId(), device);
        System.out.println("Registering device: " + device.getDeviceName());
    }


    /**
     * An inner class method to handle client connections. 
     */
    private class ClientHandler extends Thread {
        private final Socket clientSocket;


        /**
         * Constructor method for the "ClientHandler", creates an object of itself
         * @param socket The client socket to handle communication back and fourth.
         */
        public ClientHandler(Socket socket) {
            this.clientSocket = socket;
        }


        /**
         * Method will run once the thread starts-up.
         */
        @Override
        public void run() {
            // Using try-with-resources to handle the client connection
            try (ObjectOutputStream output = new ObjectOutputStream(clientSocket.getOutputStream());
                ObjectInputStream input = new ObjectInputStream(clientSocket.getInputStream())) {
            // Continously reading all actions from the client-side
            while (true) {
                // Read the action type from the client-side
                String action = input.readUTF();
                // Handles the "REGISTER" action of the device
                if (action.equals("REGISTER")) {
                    // Reading the device object from the client-side
                    Device device = (Device) input.readObject();
                    // Registeringg the device on the server-side
                    registeringDevice(device);
                    // Sending a response to the client-side
                    output.writeUTF("Device registered successfully");
                    output.flush(); 
                // Handles the "CONTROL" action of the device
                } else if (action.equals("CONTROL")) {
                    // Reading the device ID from the client-side
                    String deviceId = input.readUTF();
                    // Reading the desired state from the client whether its set true/false ON/OFF
                    boolean state = input.readBoolean();
                    synchronized (devices) {
                    
                        // Gets the device from the devices map (Map)
                        Device device = devices.get(deviceId);
                        // Checking to make sure the device actually exists
                        if (device != null) {
                            // Updating the device status
                            device.setDeviceStatus(state);
                            // Sending a response to the client-side
                            output.writeUTF("Device status updated successfully");
                            output.flush();
                        } else {
                            // Sending a response to the client if the device cannot be found
                            output.writeUTF("Device not found");
                            output.flush();
                        }
                    }
                // Handles the "STATUS" action of the device
                } else if (action.equals("STATUS")) {
                    // Reading the device ID from the client-side
                    String deviceId = input.readUTF();
                    synchronized (devices) {
                        // Getting the device from the devices map (Map)
                        Device device = devices.get(deviceId);
                        // Checking to make sure the device actually exists
                        if (device != null) {
                            // Sending the device object to the client-side
                            output.writeObject(device);
                            output.flush();
                        } else {
                            // Sends a response to the client-side if the device cannot be found.
                            output.writeUTF("Device not found");
                            output.flush();
                        }
                    }
                }
            }
        } catch (EOFException e) { // Client disconnected with error handling
            System.out.println("Client disconnected"); //
                } catch (IOException | ClassNotFoundException e) { //
                    e.printStackTrace();
                }
            }
        }
    }
