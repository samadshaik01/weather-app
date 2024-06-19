package com.nagarro.weatherapp.transformer;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import com.nagarro.weatherapp.dto.CityCoordinates;
import com.nagarro.weatherapp.entity.GeoCoordinatesEntity;

class GeoCoordinateTransformerTest {

	private GeoCoordinateTransformer geoCoordinateTransformer;

	@BeforeEach
	void setup() {
		geoCoordinateTransformer = new GeoCoordinateTransformer();
	}

	@Test
	void test_should_transform_geoCoordinate_to_citycoordinates() {
		// given
		GeoCoordinatesEntity geoCoordinatesEntity = new GeoCoordinatesEntity("1.0", "2.0");

		// CityCoordinates expected_cityCoordinates = new CityCoordinates("1.0","2.0");
		/*
		 * why i commented above line is CityCoordinates has private members ,we cannt
		 * create obj directly
		 */
		CityCoordinates expected_cityCoordinates = CityCoordinates.builder().latitude("1.0").Longitude("2.0").build();

		// when
		CityCoordinates cityCoordinates = geoCoordinateTransformer.transformToCityCoordinates(geoCoordinatesEntity);

		// then
//		assertEquals(expected_cityCoordinates.getLatitude(), cityCoordinates.getLatitude());
//		assertEquals(expected_cityCoordinates.getLongitude(), cityCoordinates.getLongitude());

		assertAll("should return city coordinates",
				() -> assertEquals(expected_cityCoordinates.getLatitude(), cityCoordinates.getLatitude()),
				() -> assertEquals(expected_cityCoordinates.getLatitude(), cityCoordinates.getLatitude()));

	}

}
