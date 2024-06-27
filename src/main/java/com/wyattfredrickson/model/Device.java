package com.wyattfredrickson.model;

import java.io.Serializable;

/**
 * Public class that will represent a single device object in the smarthome.
 */
public class Device implements Serializable {
    private static final long serialVersionUID = 1L;
    // Declare attributes/properties of the single 'device' object
    private String deviceId;
    private String deviceName;
    private boolean deviceStatus;


    /**
     * No-args constructor
     */
    public Device() {

    }


    /**
     * Constructor that is an instance of a 'device' object with all of its attributes
     * @param deviceId 
     * @param deviceName
     * @param deviceStatus
     */
    public Device(String deviceId, String deviceName, boolean deviceStatus) {
        this.deviceId = deviceId;
        this.deviceName = deviceName;
        this.deviceStatus = deviceStatus;
    }


    /**
     * 
     * @return device id
     */
    public String getDeviceId() {
        return deviceId;
    }


    /**
     * 
     * @param deviceId device id
     */
    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }


    /**
     * 
     * @return device name
     */
    public String getDeviceName() {
        return deviceName;
    }


    /**
     * 
     * @param deviceName device name
     */
    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }


    /**
     * 
     * @return
     */
    public boolean getDeviceStatus() {
        return deviceStatus;
    }


    /**
     * 
     * @param deviceStatus
     */
    public void setDeviceStatus(boolean deviceStatus) {
        this.deviceStatus = deviceStatus;
    }


}