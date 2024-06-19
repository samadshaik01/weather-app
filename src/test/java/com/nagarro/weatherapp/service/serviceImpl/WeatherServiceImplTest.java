package com.nagarro.weatherapp.service.serviceImpl;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.nagarro.weatherapp.dto.CityCoordinates;
import com.nagarro.weatherapp.dto.CityWeather;
import com.nagarro.weatherapp.dto.WeatherRequestDetails;
import com.nagarro.weatherapp.dto.WeatherResponse;
import com.nagarro.weatherapp.entity.GeoCoordinatesEntity;
import com.nagarro.weatherapp.entity.OpenWeatherResponseEntity;
import com.nagarro.weatherapp.entity.WeatherEntity;
import com.nagarro.weatherapp.provider.GeoCoordinateProvider;
import com.nagarro.weatherapp.provider.WeatherProvider;
import com.nagarro.weatherapp.service.WeatherService;
import com.nagarro.weatherapp.transformer.GeoCoordinateTransformer;
import com.nagarro.weatherapp.transformer.OpenWeatherTransformer;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(WeatherService.class)
class WeatherServiceImplTest {

	private static final String DESCRIPTION = "good";

	private static final String WEATHER = "cloudy";

	private static final String LONGITUDE = "2.1";

	private static final String LATITUDE = "1.1";

	private static final String CITY = "toronto";

	@MockBean
	private GeoCoordinateProvider geoCoordinateProvider;

	@MockBean
	private GeoCoordinateTransformer geoCoordinateTransformer;

	@MockBean
	private OpenWeatherTransformer openWeatherTransformer;

	@MockBean
	private WeatherProvider weatherProvider;

	@InjectMocks
	private WeatherServiceImpl weatherService;

	@Test
	void test_should_return_Weather_response() throws Exception {
		// given
		final WeatherRequestDetails weatherRequestDetails = WeatherRequestDetails.builder().city("toronto").build();
		final GeoCoordinatesEntity geoCoordinatesEntity = new GeoCoordinatesEntity(null, null);
		final CityCoordinates cityCoordinates = CityCoordinates.builder().latitude("1.0").Longitude("2.0").build();
		final OpenWeatherResponseEntity openWeatherResponse = new OpenWeatherResponseEntity();
		// final WeatherResponse weatherResponse
		// =WeatherResponse.builder().weather(anyString()).details(anyString()).build();
		final CityWeather cityWeather = CityWeather.builder().weather("cloudy").details("good").build();
		final WeatherResponse weatherResponse = WeatherResponse.builder().weather(cityWeather.getWeather())
				.details(cityWeather.getDetails()).build();

		final WeatherResponse expected_weatherResponse = WeatherResponse.builder().weather(cityWeather.getWeather())
				.details(cityWeather.getDetails()).build();

		when(geoCoordinateProvider.getCoordinates(weatherRequestDetails)).thenReturn(geoCoordinatesEntity);
		when(geoCoordinateTransformer.transformToCityCoordinates(geoCoordinatesEntity)).thenReturn(cityCoordinates);
		when(weatherProvider.getWeather(cityCoordinates)).thenReturn(openWeatherResponse);
		when(openWeatherTransformer.transformtoCityWeather(openWeatherResponse)).thenReturn(cityWeather);
		when(openWeatherTransformer.transformToWeatherResponse(cityWeather)).thenReturn(weatherResponse);

		// when
		final WeatherResponse actual_weatherResponse = weatherService.getWeather(weatherRequestDetails);

		// then
		assertAll("test_should_return_Weather_response",
				() -> assertEquals(expected_weatherResponse.getWeather(), actual_weatherResponse.getWeather()),
				() -> assertEquals(expected_weatherResponse.getDetails(), actual_weatherResponse.getDetails()));

	}

	// best practice
	@Test
	void test_should_return_Weather_response1() throws Exception {
		// given
		final WeatherRequestDetails weatherRequestDetails = WeatherRequestDetails.builder().city(CITY).build();

		mockGeoCoordinateProvider(weatherRequestDetails);
		mockgGeoCoordinateTransformer();
		mockWeatherProvider();
		mockOpenWeatherTransformer();
		final WeatherResponse expected_weatherResponse = WeatherResponse.builder().weather(WEATHER).details(DESCRIPTION)
				.build();

		// when
		final WeatherResponse actual_weatherResponse = weatherService.getWeather(weatherRequestDetails);

		// then
		assertAll("test_should_return_Weather_response",
				() -> assertEquals(expected_weatherResponse.getWeather(), actual_weatherResponse.getWeather()),
				() -> assertEquals(expected_weatherResponse.getDetails(), actual_weatherResponse.getDetails()));

	}

	private void mockOpenWeatherTransformer() {
		final CityWeather cityWeather = CityWeather.builder().weather(WEATHER).details(DESCRIPTION).build();
		when(openWeatherTransformer.transformtoCityWeather(any())).thenReturn(cityWeather);

		final WeatherResponse weatherResponse = WeatherResponse.builder().weather(cityWeather.getWeather())
				.details(cityWeather.getDetails()).build();
		when(openWeatherTransformer.transformToWeatherResponse(cityWeather)).thenReturn(weatherResponse);

	}

	private void mockWeatherProvider() throws Exception {

		final WeatherEntity weatherEntity = WeatherEntity.builder().main(WEATHER).description(DESCRIPTION).build();

		final WeatherEntity[] entities = { weatherEntity };

		final OpenWeatherResponseEntity openWeatherResponseEntity = OpenWeatherResponseEntity.builder()
				.weather(entities).build();

		when(weatherProvider.getWeather(any())).thenReturn(openWeatherResponseEntity);

	}

	private void mockgGeoCoordinateTransformer() {
		final CityCoordinates cityCoordinates = CityCoordinates.builder().latitude(LATITUDE).Longitude(LONGITUDE)
				.build();
		when(geoCoordinateTransformer.transformToCityCoordinates(any())).thenReturn(cityCoordinates);

	}

	private void mockGeoCoordinateProvider(WeatherRequestDetails weatherRequestDetails) throws Exception {
		final GeoCoordinatesEntity geoCoordinatesEntity = GeoCoordinatesEntity.builder().latitude(LATITUDE)
				.lonitude(LONGITUDE).build();
		when(geoCoordinateProvider.getCoordinates(weatherRequestDetails)).thenReturn(geoCoordinatesEntity);

	}

}
