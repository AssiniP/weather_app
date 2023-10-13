package com.miApp.services;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.*;

import com.miApp.models.WeatherData;
import com.miApp.models.WeatherDataRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class WeatherUpdateService {
    @Autowired
    private WeatherService weatherService;
    @Autowired
    private WeatherDataRepository weatherDataRepository;
    
    private LocalDateTime lastUpdateDateTime;
    
    @Scheduled(fixedRate = 300000) // 5 minutos
    public void updateWeatherData() {  
    	Flux<WeatherData> weatherDataFlux = weatherService.getWeatherData();
        System.out.println("toy actualizando");
        weatherDataFlux
            .doOnNext(weatherDataRepository::save)
            .doOnComplete(() -> lastUpdateDateTime = LocalDateTime.now())
            .subscribe();
    }
    
    public LocalDateTime getLastUpdateDateTime() {
        return lastUpdateDateTime;
    }
}
