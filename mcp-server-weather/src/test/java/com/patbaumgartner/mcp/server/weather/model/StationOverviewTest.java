package com.patbaumgartner.mcp.server.weather.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class StationOverviewTest {

	@Test
	public void shouldMapJsonToJavaRecord() throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.findAndRegisterModules();

		ClassPathResource responseResource = new ClassPathResource("sample/response_1745862664329.json");
		File apiResponseFile = responseResource.getFile();

		StationOverview overview = mapper.readValue(apiResponseFile, StationOverview.class);

		assertNotNull(overview);
		assertNotNull(overview.getStations());
		assertTrue(overview.getStations().containsKey("10865"));

		StationData station = overview.getStations().get("10865");
		assertNotNull(station.forecast1());
		assertEquals("10865", station.forecast1().stationId());
	}

}
