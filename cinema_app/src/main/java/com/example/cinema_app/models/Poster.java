package com.example.cinema_app.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "posters")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Poster {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long posterId;
    private String posterName;
    private String originalFileName;
    private Long size;
    private String contentType;
    @OneToOne(cascade = CascadeType.ALL)
    private Film film;
    @Lob
    private byte[] bytes;
}
