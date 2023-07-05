package websocket.poc.websocket.advancedtrade.model.ticker

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.Instant
import websocket.poc.websocket.advancedtrade.model.tickerbatch.model.AdvancedTradeTickerBatchMessageEvents

data class AdvancedTradeTickerBatchMessage(
    @JsonProperty("channel") val channel: String = "ticker_batch",
    @JsonProperty("client_id") val clientId: String,
    @JsonProperty("timestamp") val timestamp: Instant,
    @JsonProperty("sequence_num") val sequenceNumber: Long,
    @JsonProperty("events") val events: List<AdvancedTradeTickerBatchMessageEvents>,
)
