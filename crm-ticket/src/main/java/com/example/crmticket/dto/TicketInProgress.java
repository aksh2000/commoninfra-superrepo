package com.example.crmticket.dto;

import java.util.PrimitiveIterator;

public class TicketInProgress {
    private String ticketId;
    private int counter;

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }
}
