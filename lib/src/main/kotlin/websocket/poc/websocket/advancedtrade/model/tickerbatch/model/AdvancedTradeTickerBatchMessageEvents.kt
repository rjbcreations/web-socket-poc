package websocket.poc.websocket.advancedtrade.model.tickerbatch.model

import com.fasterxml.jackson.annotation.JsonProperty

class AdvancedTradeTickerBatchMessageEvents(
    @JsonProperty("type") val type: String,
    @JsonProperty("tickers") val tickers: List<AdvancedTradeTickerBatchMessageEventsTickers>
)