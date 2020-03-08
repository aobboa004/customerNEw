package com.learning.customer;

public class Client {

    private String clientID;
    private String clientName;
    private String clientCollege;
    private String clientCollegeStudentID;
    private String clientEmail;
    private String clientPhoneNumber;
    private String eventIDj;

    public Client() {
    }

    public Client(String clientID, String clientName, String clientCollege, String clientCollegeStudentID, String clientEmail, String clientPhoneNumber,String eventIDj) {
        this.clientID = clientID;
        this.clientName = clientName;
        this.clientCollege = clientCollege;
        this.clientCollegeStudentID = clientCollegeStudentID;
        this.clientEmail = clientEmail;
        this.clientPhoneNumber = clientPhoneNumber;
        this.eventIDj=eventIDj;
    }

    public String getEventIDj() {
        return eventIDj;
    }

    public void setEventIDj(String eventIDj) {
        this.eventIDj = eventIDj;
    }

    public String getClientID() {
        return clientID;
    }

    public void setClientID(String clientID) {
        this.clientID = clientID;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientCollege() {
        return clientCollege;
    }

    public void setClientCollege(String clientCollege) {
        this.clientCollege = clientCollege;
    }

    public String getClientCollegeStudentID() {
        return clientCollegeStudentID;
    }

    public void setClientCollegeStudentID(String clientCollegeStudentID) {
        this.clientCollegeStudentID = clientCollegeStudentID;
    }

    public String getClientEmail() {
        return clientEmail;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }

    public String getClientPhoneNumber() {
        return clientPhoneNumber;
    }

    public void setClientPhoneNumber(String clientPhoneNumber) {
        this.clientPhoneNumber = clientPhoneNumber;
    }
}
