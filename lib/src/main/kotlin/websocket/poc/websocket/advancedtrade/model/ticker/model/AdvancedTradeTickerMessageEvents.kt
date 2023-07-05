package websocket.poc.websocket.advancedtrade.model.ticker.model

import com.fasterxml.jackson.annotation.JsonProperty

class AdvancedTradeTickerMessageEvents(
    @JsonProperty("type") val type: String,
    @JsonProperty("tickers") val tickers: List<AdvancedTradeTickerMessageEventsTickers>
)