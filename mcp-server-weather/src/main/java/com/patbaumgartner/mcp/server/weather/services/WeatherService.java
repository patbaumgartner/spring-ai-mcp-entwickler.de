package com.patbaumgartner.mcp.server.weather.services;

import com.patbaumgartner.mcp.server.weather.model.StationOverview;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.io.BufferedReader;
import java.io.StringReader;
import java.util.List;

@Service
public class WeatherService {

	private static final Logger log = LoggerFactory.getLogger(WeatherService.class);

	// See also https://dwd.api.bund.dev/ for more OpenAPI description
	private static final String WEATHER_BASE_URL = "https://app-prod-ws.warnwetter.de/v30";

	private static final String OPEN_DATA_BASE_URL = "https://opendata.dwd.de/climate_environment/CDC/observations_germany/climate/daily/kl/recent/KL_Tageswerte_Beschreibung_Stationen.txt";

	private final RestClient weatherRestClient;

	private final RestClient openDataRestClient;

	public WeatherService(RestClient.Builder restClientBuilder) {
		this.openDataRestClient = restClientBuilder.baseUrl(OPEN_DATA_BASE_URL)
			.defaultHeader("Accept", "text/plain")
			.build();

		this.weatherRestClient = restClientBuilder.baseUrl(WEATHER_BASE_URL)
			.defaultHeader("Accept", "application/json")
			.build();

	}

	@Tool(description = "Find and retrieve the station_id for weather stations across Germany to enable precise weather data queries.")
	public String getStationIds(@ToolParam(
			description = "Station is typically just city, or a region within a city but not a state or a country.") String station)
			throws Exception {

		String stationResponse = openDataRestClient.get().retrieve().body(String.class);

		StringBuilder builder = new StringBuilder();

		BufferedReader reader = new BufferedReader(new StringReader(stationResponse));

		String line;
		boolean headerSkipped = false;

		while ((line = reader.readLine()) != null) {
			if (!headerSkipped) {
				if (line.startsWith("-----------")) {
					headerSkipped = true; // skip separator line
				}
				continue;
			}

			if (line.trim().isEmpty())
				continue; // skip empty lines

			String stationId = line.substring(0, 5).trim();
			String stationsname = line.substring(61, 102).trim();
			String bundesland = line.substring(102, 143).trim();

			String stationCoordinates = String.format("StationId: %s, Stationsname: %s, Bundesland: %s%n", stationId,
					stationsname, bundesland);
			if (stationCoordinates.contains(station)) {
				builder.append(stationCoordinates);
			}
		}
		return builder.toString();
	}

	@Tool(description = "Retrieve current and forecast weather information from German Weather Service (DWD) stations by using their specific station_id.")
	public String getStationOverview(
			@ToolParam(
					description = "List of station ids to resolve the DWD weather forecast.") List<String> stationIds)
			throws Exception {

		StationOverview stationOverview = resolveStationData(stationIds);

		return renderStationOverview(stationOverview);
	}

	private StationOverview resolveStationData(List<String> stationIds) {
		String stationParam = String.join(",", stationIds);
		return weatherRestClient.get()
			.uri(uriBuilder -> uriBuilder.path("/stationOverviewExtended")
				.queryParam("stationIds", stationParam)
				.build())
			.retrieve()
			.body(StationOverview.class);
	}

	private String renderStationOverview(StationOverview stationOverview) {
		if (stationOverview == null || stationOverview.getStations() == null) {
			return "Sorry, no station data available.";
		}

		return stationOverview.toString();
	}

}
