package com.nagarro.weatherapp.service;

import com.nagarro.weatherapp.dto.WeatherRequestDetails;
import com.nagarro.weatherapp.dto.WeatherResponse;

public interface WeatherService {

	public WeatherResponse getWeather(WeatherRequestDetails weatherRequestDetails) throws Exception;

}
