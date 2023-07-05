package websocket.poc.websocket.advancedtrade.model.heartbeat

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.Instant

data class AdvancedTradeMarketTradeSubscribeRequest(
    @JsonProperty("type") val type: String = "subscribe",
    @JsonProperty("product_ids") val productIds: List<String>,
    @JsonProperty("channel") val channel: String = "market_trades",
    @JsonProperty("signature") val signature: String,
    @JsonProperty("api_key") val key: String,
    @JsonProperty("timestamp") val timestamp: Instant,
)
