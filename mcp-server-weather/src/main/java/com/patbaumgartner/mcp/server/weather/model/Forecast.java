package com.patbaumgartner.mcp.server.weather.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record Forecast(@JsonProperty("stationId") String stationId, @JsonProperty("start") Long start,
		@JsonProperty("timeStep") Long timeStep, @JsonProperty("temperature") List<Integer> temperature,
		@JsonProperty("windSpeed") Integer windSpeed, @JsonProperty("windDirection") Integer windDirection,
		@JsonProperty("windGust") Integer windGust,
		@JsonProperty("precipitationTotal") List<Integer> precipitationTotal,
		@JsonProperty("sunshine") List<Integer> sunshine, @JsonProperty("dewPoint2m") List<Integer> dewPoint2m,
		@JsonProperty("surfacePressure") List<Integer> surfacePressure,
		@JsonProperty("humidity") List<Integer> humidity, @JsonProperty("isDay") List<Boolean> isDay,
		@JsonProperty("cloudCoverTotal") List<Integer> cloudCoverTotal,
		@JsonProperty("temperatureStd") List<Integer> temperatureStd, @JsonProperty("icon") List<Integer> icon,
		@JsonProperty("icon1h") List<Integer> icon1h,
		@JsonProperty("precipitationProbablity") Integer precipitationProbablity,
		@JsonProperty("precipitationProbablityIndex") Integer precipitationProbablityIndex) {
}
