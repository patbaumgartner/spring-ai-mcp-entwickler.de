package com.patbaumgartner.mcp.server.weather.clients;

import io.modelcontextprotocol.client.McpClient;
import io.modelcontextprotocol.client.McpSyncClient;
import io.modelcontextprotocol.spec.McpClientTransport;
import io.modelcontextprotocol.spec.McpSchema.CallToolRequest;
import io.modelcontextprotocol.spec.McpSchema.CallToolResult;
import io.modelcontextprotocol.spec.McpSchema.ListToolsResult;

import java.util.List;
import java.util.Map;

public class SampleClient {

	private final McpClientTransport transport;

	public SampleClient(McpClientTransport transport) {
		this.transport = transport;
	}

	public void run() {

		McpSyncClient client = McpClient.sync(this.transport).build();

		client.initialize();

		client.ping();

		// List and demonstrate tools
		ListToolsResult toolsList = client.listTools();
		System.out.println("Available Tools = " + toolsList);
		toolsList.tools().forEach(tool -> {
			System.out.println("Tool: " + tool.name() + ", description: " + tool.description() + ", schema: "
					+ tool.inputSchema());
		});

		CallToolResult toUpperCase = client.callTool(new CallToolRequest("toUpperCase", Map.of("input", "abc-xyz")));
		System.out.println("To Upper Case: " + toUpperCase);

		CallToolResult weatherForcastResult = client
			.callTool(new CallToolRequest("getStationOverview", Map.of("stationIds", List.of("10865", "G005"))));
		System.out.println("Weather Forecast: " + weatherForcastResult);

		client.closeGracefully();
	}

}