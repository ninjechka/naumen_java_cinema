package com.example.cinema_app.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "sessions")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long sessionId;
    @OneToOne(cascade = CascadeType.ALL)
    private Film film;
    @OneToOne
    private Hall hall;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private int price;
}
