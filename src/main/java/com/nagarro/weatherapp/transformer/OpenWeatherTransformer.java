package com.nagarro.weatherapp.transformer;

import org.springframework.stereotype.Service;

import com.nagarro.weatherapp.dto.CityWeather;
import com.nagarro.weatherapp.dto.WeatherResponse;
import com.nagarro.weatherapp.entity.OpenWeatherResponseEntity;

@Service
public class OpenWeatherTransformer {

	public CityWeather transformtoCityWeather(OpenWeatherResponseEntity openWeatherResponse) {

		return CityWeather.builder().weather(openWeatherResponse.getWeather()[0].getMain())
				.details(openWeatherResponse.getWeather()[0].getDescription()).build();
	}

	public WeatherResponse transformToWeatherResponse(CityWeather cityWeather) {

		return WeatherResponse.builder().weather(cityWeather.getWeather()).details(cityWeather.getDetails()).build();
	}

}
