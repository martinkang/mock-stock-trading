package com.example.mock_stock.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.Instant;

public class QuoteResponse {

    @JsonProperty("c")
    private double currentPrice;   // 현재가 (Current price)

    @JsonProperty("h")
    private double highPrice;      // 고가 (High price)

    @JsonProperty("l")
    private double lowPrice;       // 저가 (Low price)

    @JsonProperty("o")
    private double openPrice;      // 시가 (Open price)

    @JsonProperty("pc")
    private double prevClosePrice; // 전일 종가 (Previous close)

    @JsonProperty("t")
    private long timestamp;        // Unix timestamp

    // getters/setters
    public double getCurrentPrice() { return currentPrice; }
    public void setCurrentPrice(double currentPrice) { this.currentPrice = currentPrice; }

    public double getHighPrice() { return highPrice; }
    public void setHighPrice(double highPrice) { this.highPrice = highPrice; }

    public double getLowPrice() { return lowPrice; }
    public void setLowPrice(double lowPrice) { this.lowPrice = lowPrice; }

    public double getOpenPrice() { return openPrice; }
    public void setOpenPrice(double openPrice) { this.openPrice = openPrice; }

    public double getPrevClosePrice() { return prevClosePrice; }
    public void setPrevClosePrice(double prevClosePrice) { this.prevClosePrice = prevClosePrice; }

    public long getTimestamp() { return timestamp; }
    public void setTimestamp(long timestamp) { this.timestamp = timestamp; }

    public Instant getTimestampAsInstant() {
        return Instant.ofEpochSecond(timestamp);
    }
}
