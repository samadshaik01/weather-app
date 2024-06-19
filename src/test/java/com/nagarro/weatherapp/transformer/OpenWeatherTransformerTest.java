package com.nagarro.weatherapp.transformer;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.nagarro.weatherapp.dto.CityWeather;
import com.nagarro.weatherapp.dto.WeatherResponse;
import com.nagarro.weatherapp.entity.OpenWeatherResponseEntity;
import com.nagarro.weatherapp.entity.WeatherEntity;

class OpenWeatherTransformerTest {

	private OpenWeatherTransformer openWeatherTransformer;

	@BeforeEach
	void setup() {
		openWeatherTransformer = new OpenWeatherTransformer();
	}

	@Test
	void test_transform_openWeatherResponse_to_cityWeather() {
		// give

		final WeatherEntity weatherEntity = WeatherEntity.builder().id("1").main("cloudy").description("good").icon("i")
				.build();

		final WeatherEntity[] entities = { weatherEntity };

		final OpenWeatherResponseEntity openWeatherResponseEntity = OpenWeatherResponseEntity.builder()
				.weather(entities).build();

		final CityWeather expected_cityWeather = CityWeather.builder().weather("cloudy").details("good").build();

		// when
		final CityWeather cityWeather = openWeatherTransformer.transformtoCityWeather(openWeatherResponseEntity);

		// then
		assertAll("should transform openWeatherResponse to cityWeather",
				() -> assertEquals(expected_cityWeather.getWeather(), cityWeather.getWeather()),
				() -> assertEquals(expected_cityWeather.getDetails(), cityWeather.getDetails()));
	}

	@Test
	void test_transform_cityWeather_to_openWeatherResponse() {
		// give
		final CityWeather cityWeather = CityWeather.builder().weather("cloudy").details("good").build();
		
		final  WeatherResponse  expected_weatherResponse =WeatherResponse.builder().weather(cityWeather.getWeather()).details(cityWeather.getDetails()).build();

		// when
		final  WeatherResponse  weatherResponse = openWeatherTransformer.transformToWeatherResponse(cityWeather);

		// then
		assertAll("should transform cityWeather to openWeatherResponse",
				() -> assertEquals(expected_weatherResponse.getWeather(), weatherResponse.getWeather()),
				() -> assertEquals(expected_weatherResponse.getDetails(), weatherResponse.getDetails()));
	}

}
