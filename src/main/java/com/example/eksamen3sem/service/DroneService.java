package com.example.eksamen3sem.service;

import com.example.eksamen3sem.entity.Drone;
import com.example.eksamen3sem.repository.DroneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DroneService {

    @Autowired
    DroneRepository droneRepository;

    public Drone findByUUID(UUID uuid) {
        return droneRepository.findBySerialUuid(uuid);
    }
    public void save(Drone drone) {
        droneRepository.save(drone);
    }

    public List<Drone> findAll() {
        return droneRepository.findAll();
    }
}
