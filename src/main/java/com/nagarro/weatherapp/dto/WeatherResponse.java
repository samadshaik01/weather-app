package com.nagarro.weatherapp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class WeatherResponse {
	private String weather;
	private String details;

}
