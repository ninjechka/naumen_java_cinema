package com.example.cinema_app.dto;

/**
 * DTO объект для сущности билетов
 */
public class TicketDto {
    private int ticketId;
    private int showId;
    private String username;
    private int rowNumber;
    private int placeNumber;

    public TicketDto(int ticketId, int showId, String username, int rowNumber, int placeNumber) {
        this.ticketId = ticketId;
        this.showId = showId;
        this.username = username;
        this.rowNumber = rowNumber;
        this.placeNumber = placeNumber;
    }

    public int getPlaceNumber() {
        return placeNumber;
    }

    public void setPlaceNumber(int placeNumber) {
        this.placeNumber = placeNumber;
    }

    public int getRowNumber() {
        return rowNumber;
    }

    public void setRowNumber(int rowNumber) {
        this.rowNumber = rowNumber;
    }



    public int getShowId() {
        return showId;
    }

    public void setShowId(int showId) {
        this.showId = showId;
    }

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
