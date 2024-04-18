package com.example.mauku.domain;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Colour {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long colourid;
    private String name;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "colour")
    private List<Cat> cats;

    public Colour() {
    }

    public Colour(String name) {
        super();
        this.name = name;
    }

    public Long getColourid() {
        return colourid;
    }

    public void setColourid(Long colourid) {
        this.colourid = colourid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Cat> getCats() {
        return cats;
    }

    public void setCats(List<Cat> cats) {
        this.cats = cats;
    }

    
}
