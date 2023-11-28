package com.example.cinema_app.dto;

import java.util.Collection;

/**
 * DTO объект для сущности залов
 */
public class HallDto {

    private Long id;
    private String name;
    private Collection<Integer> rowCountCollection;
    private Collection<Integer> placeCountCollection;

    public HallDto(Long id, String name, Collection<Integer> rowCountCollection,
                   Collection<Integer> placeCountCollection) {
        this.id = id;
        this.name = name;
        this.rowCountCollection = rowCountCollection;
        this.placeCountCollection = placeCountCollection;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<Integer> getRowCountCollection() {
        return rowCountCollection;
    }

    public void setRowCountCollection(Collection<Integer> rowCountCollection) {
        this.rowCountCollection = rowCountCollection;
    }

    public Collection<Integer> getPlaceCountCollection() {
        return placeCountCollection;
    }

    public void setPlaceCountCollection(Collection<Integer> placeCountCollection) {
        this.placeCountCollection = placeCountCollection;
    }
}
