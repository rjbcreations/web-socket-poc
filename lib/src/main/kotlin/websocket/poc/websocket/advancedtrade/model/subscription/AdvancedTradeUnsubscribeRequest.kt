package websocket.poc.websocket.advancedtrade.model.subscription

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.Instant


/**
 * Generic unsubscribe request for advanced trade websocket channels.
 */
data class AdvancedTradeUnsubscribeRequest(
    @JsonProperty("type") val type: String = "unsubscribe",
    @JsonProperty("product_ids") val productIds: List<String>,
    @JsonProperty("channel") val channel: String,
    @JsonProperty("signature") val signature: String,
    @JsonProperty("api_key") val key: String,
    @JsonProperty("timestamp") val timestamp: Instant,
)
