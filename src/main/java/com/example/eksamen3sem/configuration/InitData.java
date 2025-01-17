package com.example.eksamen3sem.configuration;

import com.example.eksamen3sem.entity.*;
import com.example.eksamen3sem.service.DeliveryService;
import com.example.eksamen3sem.service.DroneService;
import com.example.eksamen3sem.service.PizzaService;
import com.example.eksamen3sem.service.StationService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class InitData implements CommandLineRunner {
    private final DroneService droneService;
    private final StationService stationService;
    private final PizzaService pizzaService;
    private final DeliveryService deliveryService;

    public InitData(DroneService droneService, StationService stationService, PizzaService pizzaService, DeliveryService deliveryService) {
        this.droneService = droneService;
        this.stationService = stationService;
        this.pizzaService = pizzaService;
        this.deliveryService = deliveryService;
    }

    @Override
    public void run(String... args) {
        if (deliveryService.findAll().isEmpty()) {
            //Station data
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

            //Drone data
            Drone drone1 = new Drone();
            drone1.setSerialUuid(UUID.randomUUID().toString());
            drone1.setDriftsstatus(Status.ENABLED);
            drone1.setStation(station1);

            Drone drone2 = new Drone();
            drone2.setSerialUuid(UUID.randomUUID().toString());
            drone2.setDriftsstatus(Status.ENABLED);
            drone2.setStation(station2);

            Drone drone3 = new Drone();
            drone3.setSerialUuid(UUID.randomUUID().toString());
            drone3.setDriftsstatus(Status.DISABLED);
            drone3.setStation(station3);

            droneService.save(drone1);
            droneService.save(drone2);
            droneService.save(drone3);

            System.out.println("Drones added to db");

            //Pizza data
            Pizza pizza1 = new Pizza();
            pizza1.setTitle("Margherita");
            pizza1.setPrice(70);

            Pizza pizza2 = new Pizza();
            pizza2.setTitle("Tandoori Chicken");
            pizza2.setPrice(100);

            Pizza pizza3 = new Pizza();
            pizza3.setTitle("Hawaiian");
            pizza3.setPrice(80);

            Pizza pizza4 = new Pizza();
            pizza4.setTitle("Vegetarian");
            pizza4.setPrice(110);

            Pizza pizza5 = new Pizza();
            pizza5.setTitle("BBQ Chicken");
            pizza5.setPrice(120);

            pizzaService.save(pizza1);
            pizzaService.save(pizza2);
            pizzaService.save(pizza3);
            pizzaService.save(pizza4);
            pizzaService.save(pizza5);

            System.out.println("Pizzas added to db");

            //Delivery data
            Delivery delivery1 = new Delivery();
            delivery1.setAddress("Hvidovrevej 30, 2650 Hvidovre");
            delivery1.setExpectedDelivery(LocalDateTime.now().plusHours(1));
            delivery1.setActualDelivery(null);
            delivery1.setDrone(null);
            delivery1.setPizza(pizza1);

            Delivery delivery2 = new Delivery();
            delivery2.setAddress("Vesterbrogade 12, 1620 København V");
            delivery2.setExpectedDelivery(LocalDateTime.now().plusHours(2));
            delivery2.setActualDelivery(null);
            delivery2.setDrone(null);
            delivery2.setPizza(pizza2);

            Delivery delivery3 = new Delivery();
            delivery3.setAddress("Hovedgaden 123, 2640 Hedehusene");
            delivery3.setExpectedDelivery(LocalDateTime.now().plusHours(3));
            delivery3.setActualDelivery(null);
            delivery3.setDrone(drone1);
            delivery3.setPizza(pizza3);

            Delivery delivery4 = new Delivery();
            delivery4.setAddress("Københavnsvej 8, 2630 Taastrup");
            delivery4.setExpectedDelivery(LocalDateTime.now().plusHours(4));
            delivery4.setActualDelivery(null);
            delivery4.setDrone(drone2);
            delivery4.setPizza(pizza4);

            Delivery delivery5 = new Delivery();
            delivery5.setAddress("Amagerbrogade 23, 2300 København S");
            delivery5.setExpectedDelivery(LocalDateTime.now().plusHours(5));
            delivery5.setActualDelivery(null);
            delivery5.setDrone(null);
            delivery5.setPizza(pizza5);

            deliveryService.save(delivery1);
            deliveryService.save(delivery2);
            deliveryService.save(delivery3);
            deliveryService.save(delivery4);
            deliveryService.save(delivery5);

            System.out.println("Deliveries added to db");
        }
    }
}
