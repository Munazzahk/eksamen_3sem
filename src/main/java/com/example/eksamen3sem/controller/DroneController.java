package com.example.eksamen3sem.controller;

import com.example.eksamen3sem.entity.Drone;
import com.example.eksamen3sem.service.DroneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController("/api")
public class DroneController {
    @Autowired
    DroneService droneService;

    @GetMapping("/drones")
    public List<Drone> getAllDrones() {
        return droneService.findAll();
    }

    @PostMapping("/drones/add")
    public ResponseEntity<?> addDrone() {
        try {
            Drone newDrone = droneService.addNewDrone();
            return ResponseEntity.status(HttpStatus.CREATED).body(newDrone); //200
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST) //400
                    .body(Map.of("error", "No station found"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR) //500
                    .body(Map.of("error", "Unexpected error: " + e.getMessage()));
        }
    }

    @PostMapping("/drones/enable")
    public ResponseEntity<?> enableDrone(Long id) {
        try {
            droneService.enableDrone(id);
            return ResponseEntity.ok(Map.of("message", "Drone status updated to enabled"));
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT) //409 error for conflicting states
                    .body(Map.of("error", e.getMessage()));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND) //404
                    .body(Map.of("error", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR) //500
                    .body(Map.of("error", "Unexpected error: " + e.getMessage()));
        }
    }

    @PostMapping("/drones/disable")
    public ResponseEntity<?> disableDrone(Long id) {
        try {
            droneService.disableDrone(id);
            return ResponseEntity.ok(Map.of("message", "Drone status updated to disabled"));
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT) //409 error for conflicting states
                    .body(Map.of("error", e.getMessage()));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND) //404
                    .body(Map.of("error", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR) //500
                    .body(Map.of("error", "Unexpected error: " + e.getMessage()));
        }
    }

    @PostMapping("/drones/retire")
    public ResponseEntity<?> retireDrone(Long id) {
        try {
            droneService.retireDrone(id);
            return ResponseEntity.ok(Map.of("message", "Drone status updated to retired"));
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT) //409 error for conflicting states
                    .body(Map.of("error", e.getMessage()));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND) //404
                    .body(Map.of("error", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR) //500
                    .body(Map.of("error", "Unexpected error: " + e.getMessage()));
        }
    }


}
