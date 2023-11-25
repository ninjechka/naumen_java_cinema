package com.example.cinema_app.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Модель данных для билетов
 */
@Table(name = "tickets")
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ticketId;
    /**
     * Сеанс
     */
    @ManyToOne(fetch = FetchType.LAZY)
    private Showtime showtime;
    /**
     * Номер ряда
     */
    private int rowNumber;
    /**
     * Номер места
     */
    private int placeNumber;
    /**
     * Пользователь(владелец билета)
     */
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
}
