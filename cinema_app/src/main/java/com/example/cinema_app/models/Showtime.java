package com.example.cinema_app.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

/**
 * Модель данных для Сеансов
 */
@Table(name = "showtime")
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Showtime {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long showtimeId;
    @ManyToMany(cascade = CascadeType.MERGE)
    private List<Film> films;
    @ManyToMany(cascade = CascadeType.MERGE)
    private List<Hall> halls;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private int price;
}
