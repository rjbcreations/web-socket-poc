package websocket.poc.websocket.advancedtrade.model.level2

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.Instant

data class AdvancedTradeLevel2SubscribeRequest(
    @JsonProperty("type") val type: String = "subscribe",
    @JsonProperty("product_ids") val productIds: List<String>,
    @JsonProperty("channel") val channel: String = "level2",
    @JsonProperty("signature") val signature: String,
    @JsonProperty("api_key") val key: String,
    @JsonProperty("timestamp") val timestamp: Instant,
)
