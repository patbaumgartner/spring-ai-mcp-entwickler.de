package com.patbaumgartner.mcp.server.weather.model;

import com.fasterxml.jackson.annotation.JsonAnySetter;

import java.util.HashMap;
import java.util.Map;

public class StationOverview {

	private final Map<String, StationData> stations = new HashMap<>();

	@JsonAnySetter
	public void addStation(String key, StationData station) {
		stations.put(key, station);
	}

	public Map<String, StationData> getStations() {
		return stations;
	}

	@Override
	public String toString() {
		return "StationOverview{" + "stations=" + stations + '}';
	}

}
