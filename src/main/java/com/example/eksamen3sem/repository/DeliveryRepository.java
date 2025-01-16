package com.example.eksamen3sem.repository;

import com.example.eksamen3sem.entity.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeliveryRepository extends JpaRepository<Delivery, Long> {
    Delivery findByAddress(String address);
    List<Delivery> findByDroneIsNull();


}
