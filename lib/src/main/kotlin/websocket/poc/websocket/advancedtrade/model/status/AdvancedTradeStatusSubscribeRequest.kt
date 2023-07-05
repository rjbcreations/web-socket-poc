package websocket.poc.websocket.advancedtrade.model.status

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.Instant

data class AdvancedTradeStatusSubscribeRequest(
    @JsonProperty("type") val type: String = "subscribe",
    @JsonProperty("product_ids") val productIds: List<String>,
    @JsonProperty("channel") val channel: String = "status",
    @JsonProperty("signature") val signature: String,
    @JsonProperty("api_key") val key: String,
    @JsonProperty("timestamp") val timestamp: Instant,
)
