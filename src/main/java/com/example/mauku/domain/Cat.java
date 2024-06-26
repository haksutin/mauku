package com.example.mauku.domain;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Cat {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private double weight;
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "colourid")
    private Colour colour;

    @ManyToOne
    @JoinColumn(name = "locationid")
    private Location location;

    public Cat() {
    }

    public Cat(String name, double weight, LocalDate date, Colour colour, Location location) {
        super();
        this.name = name;
        this.weight = weight;
        this.date = date;
        this.colour = colour;
        this.location = location;
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

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Colour getColour() {
        return colour;
    }

    public void setColour(Colour colour) {
        this.colour = colour;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Cat [id=" + id + ", name=" + name + ", weight=" + weight + ", date=" + date + ", colour=" + colour
                + ", location=" + location + "]";
    }
    
    
    
}
