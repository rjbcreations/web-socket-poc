package websocket.poc.websocket.advancedtrade.model.heartbeat

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.Instant
import websocket.poc.websocket.advancedtrade.model.markettrades.model.AdvancedTradeMarketTradeMessageEvents

data class AdvancedTradeMarketTradeMessage(
    @JsonProperty("channel") val channel: String = "market_trades",
    @JsonProperty("client_id") val clientId: String,
    @JsonProperty("timestamp") val timestamp: Instant,
    @JsonProperty("sequence_num") val sequenceNumber: Long,
    @JsonProperty("events") val events: List<AdvancedTradeMarketTradeMessageEvents>,
)
