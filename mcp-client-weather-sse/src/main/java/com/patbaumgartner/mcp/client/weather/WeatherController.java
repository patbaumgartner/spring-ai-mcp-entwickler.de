package com.patbaumgartner.mcp.client.weather;

import io.modelcontextprotocol.client.McpSyncClient;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.memory.InMemoryChatMemory;
import org.springframework.ai.mcp.SyncMcpToolCallbackProvider;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WeatherController {

	private final ChatClient chatClient;

	public WeatherController(ChatClient.Builder chatClientBuilder, List<McpSyncClient> mcpSyncClients) {
		this.chatClient = chatClientBuilder
			.defaultSystem(
					"""
							Du bist ein Assistent für die Analyse von Wetterdaten. Du erhältst strukturierte Vorhersagedaten von verschiedenen Wetterstationen.

							Dateneinheiten:
							- Temperatur in 0,1 °C
							- Startzeit Unixzeit (ms)
							- Zeitintervall (ms)
							- Niederschlag gesamt in 0,1 mm/h
							- Niederschlag pro Tag in 0,1 mm/d
							- Sonnenschein in 0,1 min
							- Luftfeuchtigkeit in 0,1 %
							- Taupunkt in 0,1 °C (2 m Höhe)
							- Luftdruck in 0,1 hPa (Bodenhöhe)

							Anweisungen:
							- Wandle alle Werte in lesbare Einheiten um.
							- Erkenne und beschreibe Trends.
							- Kommentiere fehlende Werte (null).
							- Fasse Temperatur, Niederschlag, Sonnenschein, Feuchtigkeit, Taupunkt, Wind (falls vorhanden) und Luftdruck separat zusammen.
							- Erstelle am Schluss eine kurze Wetterzusammenfassung (Wetterlage, Temperaturverlauf, Komfort, besondere Ereignisse).

							Formuliere deine Antworten klar, schrittweise und in vollständigen Sätzen. Halte sie freundlich, präzise und aufschlussreich.
							""")
			.defaultTools(new SyncMcpToolCallbackProvider(mcpSyncClients))
			.defaultAdvisors(new MessageChatMemoryAdvisor(new InMemoryChatMemory()), new SimpleLoggerAdvisor())
			.build();
	}

	@PostMapping("/ask")
	public Answer ask(@RequestBody Question question) {
		return chatClient.prompt().user(question.question()).call().entity(Answer.class);
	}

	public record Question(String question) {
	}

	public record Answer(String answer) {
	}

}