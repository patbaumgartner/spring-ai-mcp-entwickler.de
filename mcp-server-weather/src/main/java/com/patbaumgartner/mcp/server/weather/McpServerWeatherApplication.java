package com.patbaumgartner.mcp.server.weather;

import com.patbaumgartner.mcp.server.weather.services.WeatherService;
import org.springframework.ai.tool.ToolCallback;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.function.FunctionToolCallback;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class McpServerWeatherApplication {

	public static void main(String[] args) {
		SpringApplication.run(McpServerWeatherApplication.class, args);
	}

	@Bean
	public ToolCallbackProvider weatherTools(WeatherService weatherService) {
		return MethodToolCallbackProvider.builder().toolObjects(weatherService).build();
	}

	@Bean
	public ToolCallback toUpperCase() {
		return FunctionToolCallback.builder("toUpperCase", (TextInput input) -> input.input().toUpperCase())
			.inputType(TextInput.class)
			.description("Put the text to upper case")
			.build();
	}

	public record TextInput(String input) {
	}

}
