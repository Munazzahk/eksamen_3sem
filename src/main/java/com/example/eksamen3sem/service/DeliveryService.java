package com.example.eksamen3sem.service;

import com.example.eksamen3sem.entity.Delivery;
import com.example.eksamen3sem.entity.Drone;
import com.example.eksamen3sem.entity.Pizza;
import com.example.eksamen3sem.entity.Status;
import com.example.eksamen3sem.repository.DeliveryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeliveryService {
    @Autowired
    DeliveryRepository deliveryRepository;

    @Autowired
    PizzaService pizzaService;

    @Autowired
    DroneService droneService;

    public List<Delivery> findByDroneIsNull() {
        return deliveryRepository.findByDroneIsNull();
    }

    //Sorted list of non-delivered deliveries so oldest delivery is first
    //Best to be close to db but the name is quite long :)
    public List<Delivery> findByActualDelivery() {
        return deliveryRepository.findByActualDeliveryIsNullOrderByExpectedDeliveryAsc();
    }

    public void addDelivery(Long pizzaId, String address) {
        Pizza pizza = pizzaService.findById(pizzaId);

        if (pizza == null) {
            throw new RuntimeException("Pizza not found");
        }

        Delivery delivery = new Delivery();
        delivery.setPizza(pizza);
        delivery.setAddress(address);
        delivery.setExpectedDelivery(LocalDateTime.now().plusMinutes(30));
        delivery.setDrone(null);

        deliveryRepository.save(delivery);
    }

    public void scheduleDelivery(Long deliveryId) {
        Delivery delivery = deliveryRepository.findById(deliveryId)
                .orElseThrow(() -> new IllegalArgumentException("Delivery not found"));

        if (delivery.getDrone() != null) {
            throw new IllegalArgumentException("Delivery already has a drone assigned");
        }

        //Find first available drone
        Drone drone = droneService.findAll().stream()
                .filter(d -> d.getDriftsstatus() == Status.ENABLED)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No available drones in operation"));

        //Assign drone to delivery and save
        delivery.setDrone(drone);
        deliveryRepository.save(delivery);
    }

    public void finishDelivery(Long deliveryId) {
        Delivery delivery = deliveryRepository.findById(deliveryId)
                .orElseThrow(() -> new IllegalArgumentException("Delivery not found"));

        if (delivery.getDrone() == null) {
            throw new IllegalArgumentException("Delivery does not have a drone assigned");
        }

        delivery.setActualDelivery(LocalDateTime.now());
        deliveryRepository.save(delivery);
    }

}
