package websocket.poc.websocket.advancedtrade.model.ticker

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.Instant

data class AdvancedTradeTickerSubscribeRequest(
    @JsonProperty("type") val type: String = "subscribe",
    @JsonProperty("product_ids") val productIds: List<String>,
    @JsonProperty("channel") val channel: String = "ticker",
    @JsonProperty("signature") val signature: String,
    @JsonProperty("api_key") val key: String,
    @JsonProperty("timestamp") val timestamp: Instant,
)
