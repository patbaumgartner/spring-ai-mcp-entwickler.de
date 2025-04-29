package com.patbaumgartner.mcp.server.weather.clients;

import io.modelcontextprotocol.client.transport.ServerParameters;
import io.modelcontextprotocol.client.transport.StdioClientTransport;

import java.io.File;

/**
 * With stdio transport, the MCP server is automatically started by the client. But you
 * have to build the server jar first:
 *
 * <pre>
 * ./mvnw clean install -DskipTests
 * </pre>
 */
public class ClientStdio {

	public static void main(String[] args) {

		System.out.println(new File(".").getAbsolutePath());

		ServerParameters stdioParams = ServerParameters.builder("java")
			.args("-Dspring.ai.mcp.server.stdio=true", "-Dspring.main.web-application-type=none",
					"-Dlogging.pattern.console=", "-jar", "target/mcp-server-weather-0.0.1-SNAPSHOT.jar")
			.build();

		StdioClientTransport transport = new StdioClientTransport(stdioParams);

		new SampleClient(transport).run();
	}

}