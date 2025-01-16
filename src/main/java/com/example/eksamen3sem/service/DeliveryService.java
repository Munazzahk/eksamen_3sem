package com.example.eksamen3sem.service;

import com.example.eksamen3sem.entity.Delivery;
import com.example.eksamen3sem.repository.DeliveryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeliveryService {
    @Autowired
    DeliveryRepository deliveryRepository;

    public Delivery findByAddress(String address) {
        return deliveryRepository.findByAddress(address);
    }

    public List<Delivery> findByDroneIsNull() {
        return deliveryRepository.findByDroneIsNull();
    }

}
