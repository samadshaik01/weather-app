package com.nagarro.weatherapp.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.nagarro.weatherapp.dto.WeatherRequestDetails;
import com.nagarro.weatherapp.dto.WeatherResponse;
import com.nagarro.weatherapp.service.WeatherService;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(WeatherController.class)
class WeatherControllerTest {

	private static final String DETAILS = "good";

	private static final String WEATHR = "cloudy";

	private static final String CITY = "Toronto";

	@MockBean
	private WeatherService weatherService;

	@Autowired
	private MockMvc mockMvc;

	/*
	 * @InjectMocks private WeatherController weatherController;
	 */

	@Test
	void test_should_return_weatherResponse() throws Exception {

		// given
		final WeatherRequestDetails weatherRequestDetails = WeatherRequestDetails.builder().city(CITY).build();

		WeatherResponse response = WeatherResponse.builder().weather(WEATHR).details(DETAILS).build();

		when(weatherService.getWeather(weatherRequestDetails)).thenReturn(response);

		// when

		mockMvc.perform(get("/api/v1//weather/{city}", CITY)).andDo(print()).andExpect(status().isOk());

		// then

	}
}
