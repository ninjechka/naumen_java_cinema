package com.example.cinema_app.dto;

import java.time.LocalDateTime;

/**
 * DTO объект для сущности сеансов
 */
public class ShowtimeDto {

    private Long showtimeId;
    private String film;
    private String hall;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private int price;

    public ShowtimeDto(Long showtimeId, String film, String hall, LocalDateTime startTime, LocalDateTime endTime, int price) {
        this.showtimeId = showtimeId;
        this.film = film;
        this.hall = hall;
        this.startTime = startTime;
        this.endTime = endTime;
        this.price = price;
    }

    public Long getId() {
        return showtimeId;
    }

    public void setId(Long showtimeId) {
        this.showtimeId = showtimeId;
    }

    public String getFilm() {
        return film;
    }

    public void setFilm(String filmName) {
        this.film = filmName;
    }

    public String getHall() {
        return hall;
    }

    public void setHall(String hallName) {
        this.hall = hallName;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
