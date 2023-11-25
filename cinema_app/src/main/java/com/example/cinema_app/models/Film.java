package com.example.cinema_app.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Модель данных для Фильмов.
 */
@Table(name = "films")
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long filmId;
    /**
     * Название
     */
    private String filmName;
    /**
     * Жанр
     */
    private String genre;
    /**
     * Страна
     */
    private String country;
    /**
     * Год выхода
     */
    private int releaseYear;
    /**
     * Продолжительность в минутах
     */
    private int durationInMinutes;
    /**
     * Минимальный допустимый возраст
     */
    private int minimalAge;
    /**
     * Описание фильма
     */
    @Column(columnDefinition = "text")
    private String description;
    /**
     * Название файла с расширением (file.jpg)
     */
    private String poster;
}
