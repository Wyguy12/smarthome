package com.wyattfredrickson.server;


/**
 * Main method for starting the server
 */
public class MainServer {
    public static void main(String[] args) {
        DeviceServer server = new DeviceServer();
        server.startServer();
    }
}
