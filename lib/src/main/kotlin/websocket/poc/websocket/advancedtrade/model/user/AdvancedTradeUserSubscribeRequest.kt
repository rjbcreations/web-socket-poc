package websocket.poc.websocket.advancedtrade.model.user

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.Instant

data class AdvancedTradeUserSubscribeRequest(
    @JsonProperty("type") val type: String = "subscribe",
    @JsonProperty("product_ids") val productIds: List<String>,
    @JsonProperty("channel") val channel: String = "user",
    @JsonProperty("signature") val signature: String,
    @JsonProperty("api_key") val key: String,
    @JsonProperty("timestamp") val timestamp: Instant,
)
