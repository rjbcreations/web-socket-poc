package websocket.poc.websocket.advancedtrade.model.level2

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.Instant
import websocket.poc.websocket.advancedtrade.model.level2.model.AdvancedTradeLevel2MessageEvents

data class AdvancedTradeLevel2Message(
    @JsonProperty("channel") val channel: String = "l2_data",
    @JsonProperty("client_id") val clientId: String,
    @JsonProperty("timestamp") val timestamp: Instant,
    @JsonProperty("sequence_num") val sequenceNumber: Long,
    @JsonProperty("events") val events: List<AdvancedTradeLevel2MessageEvents>,
)
