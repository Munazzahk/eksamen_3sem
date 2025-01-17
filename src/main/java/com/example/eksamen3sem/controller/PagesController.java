package com.example.eksamen3sem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

//To show the actual page with ThymeLeaf template

@Controller
public class PagesController {
    @GetMapping("/deliveries")
    public String showDeliveriesPage() {
        return "deliveries";
    }

    @GetMapping("/drones")
    public String showDronesPage() {
        return "drones";
    }

    @GetMapping("/")
    public String showIndexPage() {
        return "index";
    }

    @GetMapping("/upcoming")
    public String showUpcomingPage() {
        return "upcoming";
    }
}
