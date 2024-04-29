package edu.canisius.csc213.project2.quotes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import edu.canisius.csc213.project2.util.PolygonJsonReplyTranslator;
import edu.canisius.csc213.project2.quotes.StockQuote;
import edu.canisius.csc213.project2.quotes.StockQuoteProvider;

public class PolygonStockQuoteProvider implements StockQuoteProvider {

    @Override
public String getEndpointUrl(String symbolName, String date, String apiKey) {
    // Define the expected date format
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    dateFormat.setLenient(false);
    
    try {
        // Parse the date to ensure it matches the expected format
        dateFormat.parse(date);
    } catch (ParseException e) {
        // If parsing fails, throw an IllegalArgumentException
        throw new IllegalArgumentException("Invalid date format. Expected format is YYYY-MM-DD.");
    }
    
    // Construct the URL using the provided symbol, date, and API key
    return String.format("https://api.polygon.io/v2/aggs/ticker/%s/range/1/day/%s/%s?apiKey=%s",
            symbolName, date, date, apiKey);
}


    @Override
    public StockQuote getStockQuote(String stockQuoteEndpoint) throws IOException {
        // Send the GET request to the endpoint URL and retrieve the JSON response as a string
        String json = sendGetRequest(stockQuoteEndpoint);

        // Translate the JSON response to a StockQuote object
        PolygonJsonReplyTranslator translator = new PolygonJsonReplyTranslator();
        return translator.translateJsonToFinancialInstrument(json);
    }

    public static String sendGetRequest(String endpointUrl) throws IOException {
        // Create a URL object from the endpoint URL
        URL url = new URL(endpointUrl);

        // Open a connection to the URL
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        // Set the request method to GET
        connection.setRequestMethod("GET");

        // Check the response code
        int responseCode = connection.getResponseCode();
        if (responseCode != HttpURLConnection.HTTP_OK) {
            throw new IOException("HTTP request failed with response code " + responseCode);
        }

        // Read the response from the connection's input stream
        StringBuilder response = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
        }

        // Disconnect the connection
        connection.disconnect();

        // Return the response as a string
        return response.toString();
    }
}
