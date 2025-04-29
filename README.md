# Spring AI MCP Examples

This repository provides practical examples demonstrating how to use Spring AI together with the Model Configuration Protocol (MCP), as featured in the German *JavaMagazin* article on [entwickler.de](https://entwickler.de/).

All projects are Spring Boot applications built with Spring AI, showcasing the integration of AI models into Java applications using different communication patterns and runtime configurations.

## Project Overview

- [**mcp-client-weather-node**](./mcp-client-weather-node):
  A Spring Boot application that runs a Node.js-based client communicating with the MCP server via standard input/output (STDIO) streams.

- [**mcp-client-weather-sse**](./mcp-client-weather-sse):
  A Spring Boot client using Server-Sent Events (SSE) to receive live weather forecast updates from the MCP server in real-time.

- [**mcp-client-weather-stdio**](./mcp-client-weather-stdio):
  A lightweight Spring Boot client that interacts with the MCP server through standard input/output (STDIO) communication.

- [**mcp-server-weather**](./mcp-server-weather):
  A Spring Boot server application delivering weather forecast data via the MCP protocol. It supports both STDIO and SSE modes, depending on the selected runtime configuration. Both the SSE and STDIO clients above connect to this server.

Each project resides in its respective subdirectory and demonstrates a unique method of integrating or exposing AI-powered weather services with Spring AI.
