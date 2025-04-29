package com.patbaumgartner.mcp.server.weather.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record StationData(@JsonProperty("forecast1") Forecast forecast1, @JsonProperty("forecast2") Forecast forecast2,
		@JsonProperty("forecastStart") Long forecastStart, @JsonProperty("warnings") List<String> warnings,
		// Ignoring historic data
		// @JsonProperty("days") List<DayData> days,
		@JsonProperty("threeHourSummaries") String threeHourSummaries) {
}
