package com.miApp.models;

import jakarta.persistence.*;

@Entity
public class WeatherInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String tempDesc;
    private String description;
    private Double visibility;
    private Double humidity;
    private Double pressure;
    private Double wind_speed;

    @OneToOne(mappedBy = "weather")
    private LocationData weatherData;
    
    public WeatherInfo() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTempDesc() {
		return tempDesc;
	}

	public void setTempDesc(String tempDesc) {
		this.tempDesc = tempDesc;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getVisibility() {
		return visibility;
	}

	public void setVisibility(Double visibility) {
		this.visibility = visibility;
	}

	public Double getHumidity() {
		return humidity;
	}

	public void setHumidity(Double humidity) {
		this.humidity = humidity;
	}

	public Double getPressure() {
		return pressure;
	}

	public void setPressure(Double pressure) {
		this.pressure = pressure;
	}

	public Double getWind_speed() {
		return wind_speed;
	}

	public void setWind_speed(Double wind_speed) {
		this.wind_speed = wind_speed;
	}
    
    
}