package com.example.eksamen3sem.repository;

import com.example.eksamen3sem.entity.Drone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DroneRepository extends JpaRepository<Drone, Long> {
    Drone findByDroneId(Long id);
}
