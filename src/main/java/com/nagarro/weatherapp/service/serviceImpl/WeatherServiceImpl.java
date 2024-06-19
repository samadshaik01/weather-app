package com.nagarro.weatherapp.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.weatherapp.dto.CityCoordinates;
import com.nagarro.weatherapp.dto.CityWeather;
import com.nagarro.weatherapp.dto.WeatherRequestDetails;
import com.nagarro.weatherapp.dto.WeatherResponse;
import com.nagarro.weatherapp.entity.GeoCoordinatesEntity;
import com.nagarro.weatherapp.entity.OpenWeatherResponseEntity;
import com.nagarro.weatherapp.provider.GeoCoordinateProvider;
import com.nagarro.weatherapp.provider.WeatherProvider;
import com.nagarro.weatherapp.service.WeatherService;
import com.nagarro.weatherapp.transformer.GeoCoordinateTransformer;
import com.nagarro.weatherapp.transformer.OpenWeatherTransformer;

@Service
public class WeatherServiceImpl implements WeatherService {

	@Autowired
	private GeoCoordinateProvider geoCoordinateProvider;

	@Autowired
	private GeoCoordinateTransformer geoCoordinateTransformer;

	@Autowired
	private OpenWeatherTransformer openWeatherTransformer;

	@Autowired
	private WeatherProvider weatherProvider;

	public WeatherResponse getWeather(final WeatherRequestDetails weatherRequestDetails) throws Exception {
		final GeoCoordinatesEntity geoCoordinatesEntity = geoCoordinateProvider.getCoordinates(weatherRequestDetails);
		final CityCoordinates cityCoordinates = geoCoordinateTransformer.transformToCityCoordinates(geoCoordinatesEntity);

		final OpenWeatherResponseEntity openWeatherResponse = weatherProvider.getWeather(cityCoordinates);

		final CityWeather cityWeather = openWeatherTransformer.transformtoCityWeather(openWeatherResponse);

		return openWeatherTransformer.transformToWeatherResponse(cityWeather);
	}
}
