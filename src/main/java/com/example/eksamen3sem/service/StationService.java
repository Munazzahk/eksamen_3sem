package com.example.eksamen3sem.service;

import com.example.eksamen3sem.entity.Station;
import com.example.eksamen3sem.repository.StationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StationService {

    @Autowired
    StationRepository stationRepository;

    public Station findByKoordinates(double latitude, double longitude) {
        return stationRepository.findByLatitudeAndLongitude(latitude, longitude);
    }

    public Optional<Station> findById(Long id) {
        return stationRepository.findById(id);
    }

    public void save(Station station) {
        stationRepository.save(station);
    }

    public List<Station> findAll() {
        return stationRepository.findAll();
    }
}

