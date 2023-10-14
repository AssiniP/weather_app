package com.miApp.controllers;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.miApp.models.LocationData;
import com.miApp.models.ILocationDataRepository;
import com.miApp.services.WeatherUpdateService;


@Controller
public class WeatherController {
    @Autowired
    private ILocationDataRepository weatherDataRepository;
    
    @Autowired
    private WeatherUpdateService weatherUpdateService;

    @GetMapping("/climas")
    public String mostrarClimas(Model model) {
        List<LocationData> weatherDataList = weatherDataRepository.findAll();
        model.addAttribute("climas", weatherDataList);
        
        LocalDateTime lastUpdate = weatherUpdateService.getLastUpdateDateTime();
        model.addAttribute("lastUpdate", lastUpdate);
        
        return "climas";
    }
}

