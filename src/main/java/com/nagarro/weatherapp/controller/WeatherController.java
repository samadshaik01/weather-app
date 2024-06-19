package com.nagarro.weatherapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.weatherapp.dto.WeatherRequestDetails;
import com.nagarro.weatherapp.dto.WeatherResponse;
import com.nagarro.weatherapp.service.WeatherService;

@RestController
@RequestMapping("api/v1")
public class WeatherController {

	@Autowired
	private WeatherService weatherService;

	@GetMapping("/weather/{city}")
	public @ResponseBody WeatherResponse weather(@PathVariable("city") String city) throws Exception {
		final WeatherRequestDetails weatherRequestDetails = WeatherRequestDetails.builder().city(city).build();

		return weatherService.getWeather(weatherRequestDetails);

	}

}
