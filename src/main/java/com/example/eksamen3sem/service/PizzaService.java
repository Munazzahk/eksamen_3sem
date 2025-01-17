package com.example.eksamen3sem.service;

import com.example.eksamen3sem.entity.Pizza;
import com.example.eksamen3sem.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PizzaService {
    @Autowired
    PizzaRepository pizzaRepository;

    public Pizza findById(Long id) {
        return pizzaRepository.findByPizzaId(id);
    }

    public void save(Pizza pizza) {
        pizzaRepository.save(pizza);
    }
}
