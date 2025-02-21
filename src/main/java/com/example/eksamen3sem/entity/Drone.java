package com.example.eksamen3sem.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class Drone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long droneId;

    @Column(nullable = false, unique = true)
    private String serialUuid;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status driftsstatus;

    @ManyToOne(fetch = FetchType.EAGER) //So I am sure the stations are loaded before working with drone
    @JoinColumn(name = "station_id", nullable = false)
    private Station station;

    //Getters and setters
    public Long getDroneId() {
        return droneId;
    }

    public void setDroneId(Long droneId) {
        this.droneId = droneId;
    }

    public String getSerialUuid() {
        return serialUuid;
    }

    public void setSerialUuid(String serialUuid) {
        this.serialUuid = serialUuid;
    }

    public Status getDriftsstatus() {
        return driftsstatus;
    }

    public void setDriftsstatus(Status driftsstatus) {
        this.driftsstatus = driftsstatus;
    }

    public Station getStation() {
        return station;
    }

    public void setStation(Station station) {
        this.station = station;
    }
}
