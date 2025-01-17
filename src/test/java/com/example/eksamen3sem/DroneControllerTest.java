package com.example.eksamen3sem;

import com.example.eksamen3sem.controller.DroneController;
import com.example.eksamen3sem.service.DroneService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class DroneControllerTest {

    @Mock
    private DroneService droneService;

    @InjectMocks
    private DroneController droneController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(droneController).build();
    }

    //Enable - Drone status
    @Test
    public void testEnableDrone_Success() throws Exception {
        Long droneId = 1L;
        doNothing().when(droneService).enableDrone(droneId);

        mockMvc.perform(post("/api/drones/enable")
                        .param("id", String.valueOf(droneId)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Drone status updated to enabled"));
    }

    //409 error
    @Test
    public void testDisableDrone_Conflict() throws Exception {
        Long droneId = 1L;
        doThrow(new IllegalStateException("Drone already disabled")).when(droneService).disableDrone(droneId);

        mockMvc.perform(post("/api/drones/disable")
                        .param("id", String.valueOf(droneId)))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.error").value("Drone already disabled"));
    }

    //Drone not found
    @Test
    public void testDisableDrone_NotFound() throws Exception {
        Long droneId = 999L;
        doThrow(new RuntimeException("Drone not found")).when(droneService).disableDrone(droneId);

        mockMvc.perform(post("/api/drones/disable")
                        .param("id", String.valueOf(droneId)))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error").value("Drone not found"));
    }
}
