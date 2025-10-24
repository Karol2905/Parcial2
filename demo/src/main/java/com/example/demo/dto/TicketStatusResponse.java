package com.example.demo.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data

@AllArgsConstructor
public class TicketStatusResponse {
    private Long ticketId;
    private String eventName;
    private String status;
}
