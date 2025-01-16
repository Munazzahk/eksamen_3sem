package com.example.eksamen3sem.configuration;

import com.example.eksamen3sem.entity.Drone;
import com.example.eksamen3sem.entity.Pizza;
import com.example.eksamen3sem.entity.Station;
import com.example.eksamen3sem.entity.Status;
import com.example.eksamen3sem.service.DroneService;
import com.example.eksamen3sem.service.PizzaService;
import com.example.eksamen3sem.service.StationService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class InitData implements CommandLineRunner {
    private final DroneService droneService;
    private final StationService stationService;
    private final PizzaService pizzaService;

    public InitData(DroneService droneService, StationService stationService, PizzaService pizzaService) {
        this.droneService = droneService;
        this.stationService = stationService;
        this.pizzaService = pizzaService;
    }

    @Override
    public void run(String... args) {
        Station station1 = new Station();
        station1.setLatitude(55.6761);
        station1.setLongitude(12.5683);

        Station station2 = new Station();
        station2.setLatitude(55.6570);
        station2.setLongitude(12.4737);

        Station station3 = new Station();
        station3.setLatitude(55.6480);
        station3.setLongitude(12.2922);

        stationService.save(station1);
        stationService.save(station2);
        stationService.save(station3);

        System.out.println("Stations added to db");

        Drone drone1 = new Drone();
        drone1.setSerialUuid(UUID.randomUUID());
        drone1.setDriftsstatus(Status.ENABLED);
        drone1.setStation(station1);

        Drone drone2 = new Drone();
        drone2.setSerialUuid(UUID.randomUUID());
        drone2.setDriftsstatus(Status.ENABLED);
        drone2.setStation(station2);

        Drone drone3 = new Drone();
        drone3.setSerialUuid(UUID.randomUUID());
        drone3.setDriftsstatus(Status.DISABLED);
        drone3.setStation(station3);

        droneService.save(drone1);
        droneService.save(drone2);
        droneService.save(drone3);

        System.out.println("Drones added to db");

        Pizza pizza1 = new Pizza();
        pizza1.setTitel("Margherita");
        pizza1.setPrice(70);

        Pizza pizza2 = new Pizza();
        pizza2.setTitel("Tandoori Chicken");
        pizza2.setPrice(100);

        Pizza pizza3 = new Pizza();
        pizza3.setTitel("Hawaiian");
        pizza3.setPrice(80);

        Pizza pizza4 = new Pizza();
        pizza4.setTitel("Vegetarian");
        pizza4.setPrice(110);

        Pizza pizza5 = new Pizza();
        pizza5.setTitel("BBQ Chicken");
        pizza5.setPrice(120);

        pizzaService.save(pizza1);
        pizzaService.save(pizza2);
        pizzaService.save(pizza3);
        pizzaService.save(pizza4);
        pizzaService.save(pizza5);

        System.out.println("Pizzas added to db");
    }
}
