package com.example.eksamen3sem;

import com.example.eksamen3sem.entity.Delivery;
import com.example.eksamen3sem.entity.Pizza;
import com.example.eksamen3sem.repository.DeliveryRepository;
import com.example.eksamen3sem.service.DeliveryService;
import com.example.eksamen3sem.service.DroneService;
import com.example.eksamen3sem.service.PizzaService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DeliveryServiceTest {
    @Mock
    private DeliveryRepository deliveryRepository;

    @InjectMocks
    private DeliveryService deliveryService;

    //Test to see if method works
    @Test
    public void testFindByDroneIsNull() {
        Delivery delivery1 = new Delivery();
        Delivery delivery2 = new Delivery();
        when(deliveryRepository.findByDroneIsNull()).thenReturn(Arrays.asList(delivery1, delivery2));

        List<Delivery> result = deliveryService.findByDroneIsNull();

        assertNotNull(result);
        assertEquals(2, result.size());
    }

    //No deliveries
    @Test
    public void testFindByDroneIsNull_NoDeliveries() {
        when(deliveryRepository.findByDroneIsNull()).thenReturn(Arrays.asList());
        List<Delivery> result = deliveryService.findByDroneIsNull();
        assertNotNull(result);
        assertEquals(0, result.size());
    }



}
