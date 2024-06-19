package com.nagarro.weatherapp.provider;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.nagarro.weatherapp.dto.CityCoordinates;
import com.nagarro.weatherapp.dto.CityWeather;
import com.nagarro.weatherapp.entity.GeoCoordinatesEntity;
import com.nagarro.weatherapp.entity.OpenWeatherResponseEntity;

@Service
public class WeatherProvider {

	@Value("${api.key}")
	private String apiKey;

	@Value("${weather.url}")
	private String waeatherUrl;

	public OpenWeatherResponseEntity getWeather(CityCoordinates cityCoordinates) throws Exception {
		RestTemplate restTemplate = new RestTemplate();

		final ResponseEntity<OpenWeatherResponseEntity> responseEntity;

		HttpEntity<String> requestEntity = new HttpEntity<>(null, null);

		UriComponents uriBuilder = UriComponentsBuilder.fromHttpUrl(waeatherUrl)
				.queryParam("lat", cityCoordinates.getLatitude()).queryParam("lon", cityCoordinates.getLongitude())
				.queryParam("appid", apiKey).build();
		
		System.out.println(uriBuilder.toString());

		try {
			responseEntity = restTemplate.exchange(uriBuilder.toString(), HttpMethod.GET, requestEntity,
					OpenWeatherResponseEntity.class);
		} catch (HttpStatusCodeException e) {
			throw new Exception(e.getMessage());
		}

		return responseEntity.getBody();
	}

}
