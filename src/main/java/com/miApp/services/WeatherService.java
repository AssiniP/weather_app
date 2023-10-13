package com.miApp.services;

import org.springframework.http.MediaType;
import org.springframework.stereotype.*;
import org.springframework.web.reactive.function.client.WebClient;

import com.miApp.models.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import com.fasterxml.jackson.databind.JsonNode;

@Service
public class WeatherService {
    private final WebClient webClient;
    private final String weatherApiUrl = "https://ws.smn.gob.ar/map_items/weather";

    public WeatherService(WebClient.Builder webClientBuilder) {
    	System.out.println("soy el weather service y me crearon");
        this.webClient = webClientBuilder.baseUrl(weatherApiUrl).build();
    }

    public Flux<WeatherData> getWeatherData() {
        return webClient.get()
                .retrieve()
                .bodyToFlux(JsonNode.class) // Parsear la respuesta JSON a un arreglo de JsonNode
                .map(jsonNode -> {
                    WeatherData weatherData = new WeatherData();
                    weatherData.setName(jsonNode.get("name").asText());
                    weatherData.setProvince(jsonNode.get("province").asText());

                    JsonNode weatherNode = jsonNode.get("weather");
                    WeatherInfo weatherInfo = new WeatherInfo();
                    weatherInfo.setTempDesc(weatherNode.get("tempDesc").asText());
                    weatherInfo.setDescription(weatherNode.get("description").asText());
                    weatherInfo.setVisibility(weatherNode.get("visibility").asDouble());
                    weatherInfo.setHumidity(weatherNode.get("humidity").asDouble());
                    weatherInfo.setPressure(weatherNode.get("pressure").asDouble());
                    weatherInfo.setWind_speed(weatherNode.get("wind_speed").asDouble());

                    weatherData.setWeather(weatherInfo);
                    return weatherData;
                });
    }
}
