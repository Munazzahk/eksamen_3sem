package com.example.eksamen3sem.entity;

import jakarta.persistence.*;

@Entity
public class Pizza {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pizzaId;

    @Column(nullable = false, unique = true)
    private String title;

    @Column(nullable = false)
    private int price;

    //Getters and setters
    public Long getPizzaId() {
        return pizzaId;
    }

    public void setPizzaId(Long pizzaId) {
        this.pizzaId = pizzaId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
