# Smart Home Control Demo

This is a demo project for simulating a smart home control system using JavaFX and a simple server-client architecture.

## Features

- User account creation and authentication
- Device control and status monitoring
- Real-time clock display on the user dashboard
- Drag-and-drop window functionality

## Prerequisites

- Java 22
- Maven

## Setup

1. Clone the repository:


   git clone https://github.com/yourusername/smarthome.git
   cd smarthome


smarthome
├── .vscode
├── src
│   └── main
│       └── java
│           └── com
│               └── wyattfredrickson
│                   ├── client
│                   │   ├── DeviceClient.java
│                   │   ├── UserAccountCreationUI.java
│                   │   ├── UserDashboardUI.java
│                   │   └── UserLoginUI.java
│                   ├── model
│                   │   ├── Device.java
│                   │   ├── User.java
│                   │   └── UserAccountCreation.java
│                   └── server
│                       ├── DeviceServer.java
│                       ├── MainServer.java
│                       └── UserFileManager.java
├── resources
├── test
│   └── java
│       └── com
│           └── wyattfredrickson
├── target
├── pom.xml
├── users.json
├── README.md      
└── LICENSE        