package com.example.cinema_app.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Table(name = "films")
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long filmId;
    private String filmName;
    private String genre;
    private String country;
    private int releaseYear;
    private int durationInMinutes;
    private int minimalAge;
    @Column(columnDefinition = "text")
    private String description;
    /**
     * Название файла с расширением (file.jpg)
     */
    private String poster;
}
