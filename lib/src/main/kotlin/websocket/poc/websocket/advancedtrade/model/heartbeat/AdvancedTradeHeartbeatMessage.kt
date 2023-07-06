package websocket.poc.websocket.advancedtrade.model.heartbeat

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.Instant
import websocket.poc.websocket.advancedtrade.model.heartbeat.model.AdvancedTradeHeartbeatMessageEvents

data class AdvancedTradeHeartbeatMessage(
    @JsonProperty("channel") val channel: String = "heartbeats",
    @JsonProperty("client_id") val clientId: String,
    @JsonProperty("timestamp") val timestamp: String,
    @JsonProperty("sequence_num") val sequenceNumber: Long,
    @JsonProperty("events") val events: List<AdvancedTradeHeartbeatMessageEvents>,
)
