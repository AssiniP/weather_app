package com.miApp.services;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.*;

import com.miApp.models.LocationData;
import com.miApp.models.ILocationDataRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class WeatherUpdateService {
    @Autowired
    private WeatherService weatherService;
    @Autowired
    private ILocationDataRepository locationDataRepository;
    
    private LocalDateTime lastUpdateDateTime;
    
    @Scheduled(fixedRate = 300000) // 5 minutos
    public void updateLocationData() {  
    	Flux<LocationData> locationDataFlux = weatherService.getLocationData();
        System.out.println("toy actualizando");
        locationDataFlux
            .doOnNext(locationDataRepository::save)
            .doOnComplete(() -> lastUpdateDateTime = LocalDateTime.now())
            .subscribe();
    }
    
    public LocalDateTime getLastUpdateDateTime() {
        return lastUpdateDateTime;
    }
}
