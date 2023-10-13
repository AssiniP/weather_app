package com.miApp.models;

import jakarta.persistence.*;

@Entity
public class WeatherData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    private String province;
    
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "weather_info_id")
    private WeatherInfo weather;
    
    
    public WeatherData() {}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getProvince() {
		return province;
	}


	public void setProvince(String province) {
		this.province = province;
	}


	public WeatherInfo getWeather() {
		return weather;
	}


	public void setWeather(WeatherInfo weather) {
		this.weather = weather;
	}
    
	
}
