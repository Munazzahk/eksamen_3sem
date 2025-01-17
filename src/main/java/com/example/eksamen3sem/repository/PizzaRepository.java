package com.example.eksamen3sem.repository;

import com.example.eksamen3sem.entity.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PizzaRepository extends JpaRepository<Pizza, Long> {
    Pizza findByPizzaId(Long id);
}
