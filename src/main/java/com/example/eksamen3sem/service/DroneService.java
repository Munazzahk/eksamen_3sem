package com.example.eksamen3sem.service;

import com.example.eksamen3sem.entity.Drone;
import com.example.eksamen3sem.entity.Station;
import com.example.eksamen3sem.entity.Status;
import com.example.eksamen3sem.repository.DroneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class DroneService {

    @Autowired
    DroneRepository droneRepository;

    @Autowired
    StationService stationService;

    //Used id as it is easier and faste to search by than uuid
    public Drone findById(Long id) {
        return droneRepository.findByDroneId(id);
    }
    public void save(Drone drone) {
        droneRepository.save(drone);
    }

    public List<Drone> findAll() {
        return droneRepository.findAll();
    }

    public Drone addNewDrone() {
        List<Station> stations = stationService.findAll();

        if (stations.isEmpty()) {
            throw new RuntimeException("No stations available");
        }

        Station targetStation = stationWithFewestDrones(stations);

        Drone newDrone = new Drone();
        newDrone.setSerialUuid(UUID.randomUUID());
        newDrone.setDriftsstatus(Status.ENABLED);
        newDrone.setStation(targetStation);

        droneRepository.save(newDrone);

        return newDrone;
    }

    public Station stationWithFewestDrones(List<Station> stations) {
        //Finds all the drones, groups them by stationsId, collects the groups and counts them
        Map<Long, Long> droneCount = droneRepository.findAll().stream()
                .collect(Collectors.groupingBy(
                        drone -> drone.getStation().getStationId(),
                        Collectors.counting()
                ));

        //Finds the station with the fewest drones using comparator
        //Default is 0, so a station can exist without any drones
        return stations.stream()
                .min(Comparator.comparingLong(station ->
                        droneCount.getOrDefault(station.getStationId(), 0L)))
                .orElseThrow(() -> new RuntimeException("No stations available"));

    }

    public void enableDrone(Long id) {
        Drone drone = findById(id);

        if (drone == null) {
            throw new RuntimeException("Drone not found with id: " + id);
        }
        //If the drone is already enables
        if (drone.getDriftsstatus() == Status.ENABLED) {
            throw new IllegalStateException("Drone is already enabled");
        }
        drone.setDriftsstatus(Status.ENABLED);
        droneRepository.save(drone);
    }

    public void disableDrone(Long id) {
        Drone drone = findById(id);

        if (drone == null) {
            throw new RuntimeException("Drone not found with id: " + id);
        }
        //If the drone is already disabled
        if (drone.getDriftsstatus() == Status.DISABLED) {
            throw new IllegalStateException("Drone is already disabled");
        }
        drone.setDriftsstatus(Status.DISABLED);
        droneRepository.save(drone);
    }

    public void retireDrone(Long id) {
        Drone drone = findById(id);

        if (drone == null) {
            throw new RuntimeException("Drone not found with id: " + id);
        }
        //If the drone is already retired
        if (drone.getDriftsstatus() == Status.RETIRE) {
            throw new IllegalStateException("Drone is already retired");
        }
        drone.setDriftsstatus(Status.RETIRE);
        droneRepository.save(drone);
    }


}
