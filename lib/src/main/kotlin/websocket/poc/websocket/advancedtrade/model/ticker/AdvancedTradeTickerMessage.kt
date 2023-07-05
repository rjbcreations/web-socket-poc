package websocket.poc.websocket.advancedtrade.model.ticker

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.Instant
import websocket.poc.websocket.advancedtrade.model.ticker.model.AdvancedTradeTickerMessageEvents

data class AdvancedTradeTickerMessage(
    @JsonProperty("channel") val channel: String = "ticker",
    @JsonProperty("client_id") val clientId: String,
    @JsonProperty("timestamp") val timestamp: Instant,
    @JsonProperty("sequence_num") val sequenceNumber: Long,
    @JsonProperty("events") val events: List<AdvancedTradeTickerMessageEvents>,
)
