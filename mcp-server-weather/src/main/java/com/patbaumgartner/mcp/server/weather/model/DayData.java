package com.patbaumgartner.mcp.server.weather.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record DayData(@JsonProperty("stationId") String stationId, @JsonProperty("dayDate") String dayDate,
		@JsonProperty("temperatureMin") Integer temperatureMin, @JsonProperty("temperatureMax") Integer temperatureMax,
		@JsonProperty("precipitation") Integer precipitation, @JsonProperty("windSpeed") Integer windSpeed,
		@JsonProperty("windGust") Integer windGust, @JsonProperty("windDirection") Integer windDirection,
		@JsonProperty("sunshine") Integer sunshine, @JsonProperty("sunrise") Long sunrise,
		@JsonProperty("sunset") Long sunset, @JsonProperty("moonrise") Long moonrise,
		@JsonProperty("moonset") Long moonset, @JsonProperty("moonriseOnThisDay") Long moonriseOnThisDay,
		@JsonProperty("moonsetOnThisDay") Long moonsetOnThisDay,
		@JsonProperty("sunriseOnThisDay") Long sunriseOnThisDay, @JsonProperty("sunsetOnThisDay") Long sunsetOnThisDay,
		@JsonProperty("moonPhase") Integer moonPhase, @JsonProperty("icon") Integer icon,
		@JsonProperty("icon1") Integer icon1, @JsonProperty("icon2") Integer icon2) {
}
