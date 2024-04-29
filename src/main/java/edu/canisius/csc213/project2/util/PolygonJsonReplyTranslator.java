package edu.canisius.csc213.project2.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.canisius.csc213.project2.quotes.StockQuote;

import java.io.IOException;

public class PolygonJsonReplyTranslator {

    public StockQuote translateJsonToFinancialInstrument(String json) throws IOException {
        // Create an ObjectMapper instance for JSON parsing
        ObjectMapper objectMapper = new ObjectMapper();
        
        // Parse the JSON string to a JsonNode object
        JsonNode rootNode = objectMapper.readTree(json);
        
        // Check if the expected "results" array is present in the JSON
        if (!rootNode.has("results") || !rootNode.get("results").isArray()) {
            throw new IOException("Invalid JSON format: 'results' array not found.");
        }
        
        // Get the first item from the "results" array (assuming it's the data we need)
        JsonNode resultNode = rootNode.get("results").get(0);
        
        // Extracting data from the JSON response
        String symbol = rootNode.get("ticker").asText();
        double closePrice = resultNode.get("c").asDouble();
        double highestPrice = resultNode.get("h").asDouble();
        double lowestPrice = resultNode.get("l").asDouble();
        int numberOfTransactions = resultNode.get("n").asInt();
        double openPrice = resultNode.get("o").asDouble();
        long timestamp = resultNode.get("t").asLong();
        double tradingVolume = resultNode.get("v").asDouble();
        
        // Create and return a new StockQuote object with the extracted data
        return new StockQuote(
            symbol, closePrice, highestPrice, lowestPrice, numberOfTransactions, openPrice, timestamp, tradingVolume
        );
    }
}
