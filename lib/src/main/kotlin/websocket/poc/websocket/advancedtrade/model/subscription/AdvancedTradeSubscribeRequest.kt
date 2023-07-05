package websocket.poc.websocket.advancedtrade.model.subscription

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * Generic subscribe request for advanced trade websocket channels.
 */
data class AdvancedTradeSubscribeRequest(
    @JsonProperty("type") val type: String = "subscribe",
    @JsonProperty("product_ids") val productIds: List<String>,
    @JsonProperty("channel") val channel: String,
    @JsonProperty("api_key") val key: String,
    @JsonProperty("timestamp") val timestamp: String,
    @JsonProperty("signature") val signature: String,
)
