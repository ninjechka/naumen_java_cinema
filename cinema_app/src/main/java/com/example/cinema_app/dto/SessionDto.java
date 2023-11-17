package com.example.cinema_app.dto;

import java.time.LocalTime;

public class SessionDto {

    private Long id;
    private String film;
    private String hall;
    private LocalTime startTime;
    private LocalTime endTime;
    private int price;

    public SessionDto(Long id, String film, String hall, LocalTime startTime, LocalTime endTime, int price) {
        this.id = id;
        this.film = film;
        this.hall = hall;
        this.startTime = startTime;
        this.endTime = endTime;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
