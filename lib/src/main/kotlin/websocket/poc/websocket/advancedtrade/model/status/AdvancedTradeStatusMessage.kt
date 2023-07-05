package websocket.poc.websocket.advancedtrade.model.status

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.Instant
import websocket.poc.websocket.advancedtrade.model.status.model.AdvancedTradeStatusMessageEvents

data class AdvancedTradeStatusMessage(
    @JsonProperty("channel") val channel: String = "status",
    @JsonProperty("client_id") val clientId: String,
    @JsonProperty("timestamp") val timestamp: Instant,
    @JsonProperty("sequence_num") val sequenceNumber: Long,
    @JsonProperty("events") val events: List<AdvancedTradeStatusMessageEvents>,
)
