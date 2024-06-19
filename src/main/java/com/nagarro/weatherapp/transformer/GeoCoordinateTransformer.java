package com.nagarro.weatherapp.transformer;

import org.springframework.stereotype.Service;

import com.nagarro.weatherapp.dto.CityCoordinates;
import com.nagarro.weatherapp.entity.GeoCoordinatesEntity;

@Service
public class GeoCoordinateTransformer {
	
	public CityCoordinates transformToCityCoordinates(final GeoCoordinatesEntity geoCoordinatesEntity) {
		return CityCoordinates.builder().latitude(geoCoordinatesEntity.getLatitude())
				.Longitude(geoCoordinatesEntity.getLonitude()).build();
	}

}
