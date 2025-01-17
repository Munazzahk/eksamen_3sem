package com.example.eksamen3sem.controller;


import com.example.eksamen3sem.entity.Delivery;
import com.example.eksamen3sem.service.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class DeliveryController {
    @Autowired
    DeliveryService deliveryService;

    @GetMapping("/api/deliveries")
    public List<Delivery> allUndeliveredDeliveries() {
        return deliveryService.findByActualDelivery();
    }

    @PostMapping("/api/deliveries/add")
    public ResponseEntity<Map<String, String>> addDelivery() {
        try {
            //Not adding pizzaID or address for now, but maybe in the future
            deliveryService.addDelivery();
            return ResponseEntity.status(HttpStatus.CREATED) //Created at once
                    .body(Map.of("success", "Delivery added successfully")); //201
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST) //400
                    .body(Map.of("error", e.getMessage())); //message specified in pizzaService
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR) //500
                    .body(Map.of("error", "Unexpected error: " + e.getMessage()));
        }
    }

    @GetMapping("/api/deliveries/queue")
    public List<Delivery> allDeliveriesWithoutDrone() {
        return deliveryService.findByDroneIsNull();
    }

    @PostMapping("/api/deliveries/schedule")
    public ResponseEntity<Map<String, String>> scheduleDelivery(@RequestParam Long deliveryId) {
        try {
            deliveryService.scheduleDelivery(deliveryId);
            return ResponseEntity.ok(Map.of("success", "Delivery scheduled successfully")); //200
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST) //400
                    .body(Map.of("error", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR) //500
                    .body(Map.of("error", "Unexpected error occurred while scheduling delivery"));
        }
    }

    @PostMapping("/api/deliveries/finish")
    public ResponseEntity<Map<String, String>> finishDelivery(@RequestParam Long deliveryId) {
        try {
            deliveryService.finishDelivery(deliveryId);
            return ResponseEntity.ok(Map.of("success", "Delivery finished successfully")); //200
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST) //400
                    .body(Map.of("error", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR) //500
                    .body(Map.of("error", "Unexpected error occurred while finishing delivery"));
        }
    }

}
