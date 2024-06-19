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

import com.nagarro.weatherapp.dto.WeatherRequestDetails;
import com.nagarro.weatherapp.entity.GeoCoordinatesEntity;

@Service
public class GeoCoordinateProvider {

	@Value("${api.key}")
	private String apiKey;

	@Value("${geocoordinates.url}")
	private String geoCoordinatesUrl;

	public GeoCoordinatesEntity getCoordinates(final WeatherRequestDetails WeatherRequetDetails) throws Exception {

		RestTemplate restTemplate = new RestTemplate();

		final ResponseEntity<GeoCoordinatesEntity[]> responseEntity;

		HttpEntity<String> requestEntity = new HttpEntity<>(null, null);

		UriComponents uriBuilder = UriComponentsBuilder.fromHttpUrl(geoCoordinatesUrl)
				.queryParam("q", WeatherRequetDetails.getCity()).queryParam("limit", "1").queryParam("appid", apiKey)
				.build();
		
		System.out.println(uriBuilder.toString());

		try {
			responseEntity = restTemplate.exchange(uriBuilder.toString(), HttpMethod.GET, requestEntity,
					GeoCoordinatesEntity[].class);
		} catch (HttpStatusCodeException e) {
			throw new Exception(e.getMessage());
		}

		return responseEntity.getBody()[0];

	}
}
