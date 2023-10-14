package com.miApp.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.*;

import com.miApp.models.LocationData;
import com.miApp.models.ILocationDataRepository;

import reactor.core.publisher.Flux;

@Service
public class WeatherService {
    @Autowired
    private WeatherApiService weatherApiService;
    @Autowired
    private ILocationDataRepository locationDataRepository;
    
    private LocalDateTime lastUpdateDateTime;
    
    @Scheduled(fixedRate = 300000) // 5 minutos
    public void updateLocationData() {  
    	Flux<LocationData> locationDataFlux = weatherApiService.getLocationData();
        locationDataFlux
	        .doOnNext(newLocationData -> {
	            LocationData existingLocationData = locationDataRepository.findLocationDataByNameAndProvince(newLocationData.getName(), newLocationData.getProvince());
	            if (existingLocationData != null) {
	            	existingLocationData.getWeather().updateFrom(newLocationData.getWeather());
	            } else {
	            	locationDataRepository.save(newLocationData);
	            }
	        })
            .doOnComplete(() -> lastUpdateDateTime = LocalDateTime.now())
            .subscribe();
    }
    
    public LocalDateTime getLastUpdateDateTime() {
        return lastUpdateDateTime;
    }
    
    public List<LocationData> getAllLocationData(){
    	return locationDataRepository.findAll();
    }
}
