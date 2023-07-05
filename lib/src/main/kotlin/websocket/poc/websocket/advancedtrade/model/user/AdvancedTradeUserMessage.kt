package websocket.poc.websocket.advancedtrade.model.user

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.Instant
import websocket.poc.websocket.advancedtrade.model.user.model.AdvancedTradeUserMessageEvents

class AdvancedTradeUserMessage(
    @JsonProperty("channel") val channel: String = "user",
    @JsonProperty("client_id") val clientId: String,
    @JsonProperty("timestamp") val timestamp: Instant,
    @JsonProperty("sequence_num") val sequenceNumber: Long,
    @JsonProperty("events") val events: List<AdvancedTradeUserMessageEvents>,
)