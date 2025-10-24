package com.example.demo.model;


public class Ticket {
    private Long id;
    private String userEmail;
    private String eventName;
    private String status; // "CONFIRMED", "PENDING", "CANCELLED"


    public Ticket(Long id, String userEmail, String eventName, String status) {
        this.id = id;
        this.userEmail = userEmail;
        this.eventName = eventName;
        this.status = status;
    }

    public Long getId() { return id; }
    public String getUserEmail() { return userEmail; }
    public String getEventName() { return eventName; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
