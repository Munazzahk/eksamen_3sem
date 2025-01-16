package com.example.eksamen3sem.repository;

import com.example.eksamen3sem.entity.Station;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StationRepository extends JpaRepository<Station, Long> {
    Station findByLatitudeAndLongitude(double latitude, double longitude);
}
