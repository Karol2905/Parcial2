package com.example.demo.dto;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class TicketPurchaseRequest {
    private String userEmail;
    private String eventName;
}
