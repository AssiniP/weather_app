package com.miApp.controllers;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.miApp.models.LocationData;
import com.miApp.models.ILocationDataRepository;
import com.miApp.services.WeatherService;


@Controller
public class WeatherController {
    @Autowired
    private WeatherService weatherService;

    @GetMapping("/climas")
    public String mostrarClimas(Model model) {
        model.addAttribute("climas", weatherService.getAllLocationData());
        model.addAttribute("lastUpdate", weatherService.getLastUpdateDateTime());
        
        return "climas";
    }
}

