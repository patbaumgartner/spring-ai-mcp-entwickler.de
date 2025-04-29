# Abfrage einer Wettervorhersage für Deutschland

**Hinweis:** Nicht alle Wetterstationen liefern Daten. In den Beispielen wird "Kempten" verwendet, da dort Daten verfügbar sind.

Sobald der Client läuft, hört er auf Port `8081`.

Sie können dann Fragen stellen, indem Sie eine POST-Anfrage an den `/ask`-Endpunkt senden, mit einem JSON-Payload, der eine "question"-Eigenschaft enthält.

## Beispiele mit `curl`

### Wettervorhersage für Kempten morgen

```bash
curl -H "Content-Type: application/json" \
     -X POST http://localhost:8081/ask \
     -d '{"question":"Wie ist die Wettervorhersage für Kempten für morgen?"}'
```

### Wettervorhersage für Kempten am Wochenende

```bash
curl -H "Content-Type: application/json" \
     -X POST http://localhost:8081/ask \
     -d '{"question":"Wie wird das Wetter in Kempten am kommenden Wochenende?"}'
```

### Wettervorhersage für ein bestimmtes Datum in Kempten

```bash
curl -H "Content-Type: application/json" \
     -X POST http://localhost:8081/ask \
     -d '{"question":"Wird es am 5. Mai in Kempten regnen?"}'
```