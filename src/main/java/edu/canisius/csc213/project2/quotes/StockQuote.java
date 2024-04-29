package edu.canisius.csc213.project2.quotes;

public class StockQuote {
    // Properties
    private String symbol;
    private double closePrice;
    private double highestPrice;
    private double lowestPrice;
    private int numberOfTransactions;
    private double openPrice;
    private long timestamp;
    private double tradingVolume;

    // Constructor
    public StockQuote(String symbol, double closePrice, double highestPrice, double lowestPrice, int numberOfTransactions, double openPrice, long timestamp, double tradingVolume) {
        this.symbol = symbol;
        this.closePrice = closePrice;
        this.highestPrice = highestPrice;
        this.lowestPrice = lowestPrice;
        this.numberOfTransactions = numberOfTransactions;
        this.openPrice = openPrice;
        this.timestamp = timestamp;
        this.tradingVolume = tradingVolume;
    }

    // Getters
    public String getSymbol() {
        return symbol;
    }

    public double getClosePrice() {
        return closePrice;
    }

    public double getHighestPrice() {
        return highestPrice;
    }

    public double getLowestPrice() {
        return lowestPrice;
    }

    public int getNumberOfTransactions() {
        return numberOfTransactions;
    }

    public double getOpenPrice() {
        return openPrice;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public double getTradingVolume() {
        return tradingVolume;
    }

    // Method to pretty print the stock quote information
    public String prettyPrint() {
        return String.format(
            "Symbol: %s\nClose Price: %.1f\nHighest Price: %.1f\nLowest Price: %.1f\nNumber of Transactions: %d\nOpen Price: %.1f\nTrading Volume: %.1f\n",
            symbol, closePrice, highestPrice, lowestPrice, numberOfTransactions, openPrice, tradingVolume
        );
    }
}
