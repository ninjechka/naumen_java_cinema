package com.example.cinema_app.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.List;

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
    private LocalTime startTime;
    private LocalTime endTime;
    private int price;
}
