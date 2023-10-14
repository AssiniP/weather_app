package com.miApp.services;

import org.springframework.http.MediaType;
import org.springframework.stereotype.*;
import org.springframework.web.reactive.function.client.WebClient;

import com.miApp.models.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import com.fasterxml.jackson.databind.JsonNode;

@Service
public class WeatherApiService {
    private final WebClient webClient;
    private final String weatherApiUrl = "https://ws.smn.gob.ar/map_items/weather";

    public WeatherApiService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(weatherApiUrl).build();
    }

    public Flux<LocationData> getLocationData() {
        return webClient.get()
                .retrieve()
                .bodyToFlux(JsonNode.class)
                .map(jsonNode -> {
                    LocationData locationData = new LocationData();
                    locationData.setName(jsonNode.get("name").asText());
                    locationData.setProvince(jsonNode.get("province").asText());

                    JsonNode weatherNode = jsonNode.get("weather");
                    WeatherInfo weatherInfo = new WeatherInfo();
                    weatherInfo.setTempDesc(weatherNode.get("tempDesc").asText());
                    weatherInfo.setDescription(weatherNode.get("description").asText());
                    weatherInfo.setVisibility(weatherNode.get("visibility").asDouble());
                    weatherInfo.setHumidity(weatherNode.get("humidity").asDouble());
                    weatherInfo.setPressure(weatherNode.get("pressure").asDouble());
                    weatherInfo.setWind_speed(weatherNode.get("wind_speed").asDouble());

                    locationData.setWeather(weatherInfo);
                    return locationData;
                });
    }
}
