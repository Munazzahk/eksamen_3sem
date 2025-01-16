package com.example.eksamen3sem.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api")
public class DroneController {
    @GetMapping("/drones")
    public String getMessage() {
        return "Hello from the backend!";
    }


}
