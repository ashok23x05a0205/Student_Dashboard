package com.example.demo.Entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String message;
    private LocalDate date;

    // Constructors
    public Notification() {}
    public Notification(String message, LocalDate date) {
        this.message = message;
        this.date = date;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }
}